import groovy.swing.SwingBuilder
import groovyx.gpars.dataflow.DataflowBroadcast
import groovyx.gpars.dataflow.DataflowReadChannel
import groovyx.gpars.dataflow.operator.DataflowOperator
import groovyx.gpars.group.NonDaemonPGroup
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.util.concurrent.Semaphore
import javax.swing.plaf.metal.MetalLookAndFeel
import javax.swing.*

new SwingLifeGameWithDataflowOperators(20, 18).run()


class SwingLifeGameWithDataflowOperators {
    /* Controls the game */
    final def initialGrid = []  //initial values entered by the user
    final List<List<DataflowBroadcast>> channelGrid = []  //the sequence of life values (0 or 1) for each cell
    final List<List<DataflowReadChannel>> printingGrid = []  //the sequence of life values (0 or 1) for each cell to read by the print method
    final List<List<DataflowOperator>> operatorGrid = []  //the grid of operators calculating values for their respective cells
    final DataflowBroadcast heartbeats = new DataflowBroadcast()  //gives pace to the calculation
    private final gridWidth
    private final gridHeight
    private final group = new NonDaemonPGroup()  //the thread pool to use by all the operators

    private final SwingBuilder builder = new SwingBuilder()
    private JFrame frame
    private JLabel iteration
    private JPanel scene
    private List<List<JButton>> visualCells = []  //refers to the visual cells in the UI
    private final Semaphore nextGenerationPermit = new Semaphore(0)

    SwingLifeGameWithDataflowOperators(final gridWidth, final gridHeight) {
        this.gridWidth = gridWidth
        this.gridHeight = gridHeight
        setupUI()
        setupCells()
        setupOperators()
    }

    void run() {
        evolve(0)
    }

    private void setupUI() {
        UIManager.setLookAndFeel(new MetalLookAndFeel())
        frame = builder.frame(title: "Game of Life", defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
            vbox {
                hbox {
                    JLabel caption = label('Iteration # ')
                    iteration = label('0')
                    final Font font = new Font(caption.font.name, caption.font.style, 18)
                    caption.font = font
                    iteration.font = font
                }

                scene = builder.panel()
                scene.layout = new GridLayout(gridHeight, gridWidth)
                (0..<gridHeight).each {rowIndex ->
                    def cellRow = []
                    (0..<gridWidth).each {columnIndex ->
                        final JPanel cell = builder.panel()
                        scene.add(cell)
                        def b = builder.button(' ', enabled: false)
                        cell.add(b)
                        cellRow.add(b)
                    }
                    visualCells.add(cellRow)
                }
                hbox {
                    button(text: 'Start', id: 'startButton', actionPerformed: {
                        nextGenerationPermit.release()
                        startButton.enabled = false
                        pauseButton.enabled = true

                    })
                    button(text: 'Pause', id: 'pauseButton', enabled: false, actionPerformed: {
                        nextGenerationPermit.acquire()
                        pauseButton.enabled = false
                        startButton.enabled = true
                    })
                }
            }
        }
        frame.visible = true
        frame.pack()
    }

    private def setupOperators() {
        (0..<gridHeight).each {rowIndex ->
            def operatorRow = []
            (0..<gridWidth).each {columnIndex ->
                def inputChannels = [channelGrid[rowIndex][columnIndex].createReadChannel()]
                [rowIndex - 1, rowIndex, rowIndex + 1].each {currentRowIndex ->
                    if (currentRowIndex in 0..<gridHeight) {
                        if (columnIndex > 0) inputChannels.add(channelGrid[currentRowIndex][columnIndex - 1].createReadChannel())
                        if (currentRowIndex != rowIndex) inputChannels.add(channelGrid[currentRowIndex][columnIndex].createReadChannel())
                        if (columnIndex < gridWidth - 1) inputChannels.add(channelGrid[currentRowIndex][columnIndex + 1].createReadChannel())
                    }
                }
                inputChannels.add(heartbeats.createReadChannel())

                final Closure code = new SwingLifeClosure(this, inputChannels.size)
                operatorRow[columnIndex] = group.operator(inputs: inputChannels, outputs: [channelGrid[rowIndex][columnIndex]], code)
            }
            operatorGrid.add(operatorRow)
        }
    }

    private def setupCells() {
        final Random random = new Random()
        (0..<gridHeight).each {rowIndex ->
            def initialRow = []
            def valueRow = []
            List<DataflowBroadcast> channelRow = []
            (0..<gridWidth).each {
                initialRow[it] = randomInitialValue(random)
                channelRow[it] = new DataflowBroadcast()
                valueRow[it] = channelRow[it].createReadChannel()
            }
            initialGrid.add(initialRow)
            printingGrid.add(valueRow)
            group.operator(valueRow, [], new SwingLifePrintClosure(this, valueRow.size(), visualCells[rowIndex]))
            channelGrid.add(channelRow)
        }
    }

    private int randomInitialValue(final Random random) {
        final int value = random.nextInt(100)
        return value > 49 ? 1 : 0
    }

    void evolve(def generation) {
        //initialize the dataflow network by copying the values to the cells (channels)
        (0..<gridHeight).each {rowIndex ->
            (0..<gridWidth).each {columnIndex ->
                channelGrid[rowIndex][columnIndex] << initialGrid[rowIndex][columnIndex]
            }
        }

        while (true) {
            heartbeats << 'go!'  //This message is sent to all operators to trigger the calculation of the next generation
            builder.edt {
                ++generation
                iteration.text = generation
            }
            nextGenerationPermit.acquire()
            sleep 1000
            nextGenerationPermit.release()
        }
    }
}

class SwingLifeClosure extends Closure {
    final int numberOfArguments

    SwingLifeClosure(final Object owner, final int numberOfArguments) {
        super(owner)
        this.numberOfArguments = numberOfArguments
    }

    @Override
    final int getMaximumNumberOfParameters() {
        return numberOfArguments
    }

    @Override
    Object call(Object[] args) {
        def result = args[0]
        def mates = args[1..-2].findAll {it > 0}.size()
        if (mates > 3) result = 0
        else if (mates == 3) result = 1
        else if (result == 1 && mates == 2)
            result = 1
        else
            result = 0

        bindOutput result
    }
}

final class SwingLifePrintClosure extends SwingLifeClosure {
    private final List<JButton> visualCellRow

    SwingLifePrintClosure(final Object owner, final int numberOfArguments, final List<JButton> visualCellRow) {
        super(owner, numberOfArguments)
        this.visualCellRow = visualCellRow
    }

    @Override
    Object call(Object[] args) {
        final row = visualCellRow
        SwingUtilities.invokeLater {
            args.eachWithIndex {value, index ->
                row[index].background = value == 1 ? Color.BLUE : Color.WHITE
            }
        }
    }
}