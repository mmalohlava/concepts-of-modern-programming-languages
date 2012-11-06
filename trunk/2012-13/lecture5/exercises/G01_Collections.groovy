import groovy.swing.SwingBuilder
import groovyx.gpars.GParsPool
import javax.swing.JFrame
import javax.swing.JSlider
import javax.swing.SwingConstants
import javax.swing.SwingUtilities

/**
 * @author Vaclav Pech
 */

class FileProcessor {
    private static final Random random = new Random()
    public static final MAX_WORDS = 10000

    String name
    JSlider slider

    public void perform() {
        final int wordCount = random.nextInt(MAX_WORDS)
        for (counter in (1..wordCount)) {
            if (counter % 100 == 0) {
                SwingUtilities.invokeLater { (slider.value = (int) (counter / 100)) }
//                Thread.sleep 100
                1.upto(100000) { new Random().nextInt(30) }
            }
        }
    }
}

def processors = (1..14).collect {
    final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, (int) (FileProcessor.MAX_WORDS / 100), 0)
    new FileProcessor(name: 'File' + it, slider: slider)
}

final JFrame frame = new SwingBuilder().frame(title: 'Parallelizer Demo') {
    vbox() {
        panel {
            vbox() {
                processors.each { processor ->
                    panel {
                        hbox {
                            label(text: bind(source: processor.slider, sourceProperty: 'value', converter: { "Current value: $it" }))
                            def currentPanel = panel()
                            currentPanel.add processor.slider
                        }
                    }
                }
            }
        }
        panel {
            vbox {
                panel {
                    hbox {
                        buttonGroup().with {
                            add radioButton(text: 'Single threaded', id: 'singleThreaded', selected: true)
                            add radioButton(text: 'Multithreaded threaded', id: 'mutliThreaded')
                        }
                    }
                }
                panel {
                    hbox() {
                        button(text: 'Start', actionPerformed: {
                            doOutside {
                                if (singleThreaded.isSelected()) {
                                    processors.each {
                                        it.perform()
                                    }
                                } else {
                                    GParsPool.withPool {
                                        processors.eachParallel {
                                            it.perform()
                                        }
                                    }
                                }
                            }
                        })
                        button(text: 'Clear', actionPerformed: {
                            processors.each { it.slider.value = 0 }
                        })
                    }
                }
            }
        }
    }
}

frame.visible = true
frame.pack()