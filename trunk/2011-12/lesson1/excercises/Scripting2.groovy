import groovy.swing.SwingBuilder

import java.awt.Font
import javax.swing.JTextArea

SwingBuilder builder = new SwingBuilder()
def frame
def customPanel
frame = builder.frame(title: 'Groovy scripting') {
    vbox {
        def slider = slider(id: 'slider', value: 50)
        def label = label(text: bind(source: slider, sourceProperty: 'value', converter: {"Current value: $it"}))
        scrollPane {
            def codePane = textArea(id: 'codePane', columns: 60, rows: 20)
            setFontSize(codePane)
        }
        hbox {
            customPanel = panel() {
                button('Run', actionPerformed: {
                    doOutside {
                        new GroovyShell().run(codePane.text, "Scripting", [])
                    }
                })

                button('Evaluate in context', actionPerformed: {
                    evaluateScript(codePane, frame, slider, label, customPanel)
                })

                button('Implement Runnable', actionPerformed: {
                    doOutside {
                        def classDefinition = new GroovyShell().evaluate(codePane.text)
                        Runnable task = classDefinition.newInstance()
                        new Thread(task).start()
                    }
                })
            }
        }
    }
}

private def setFontSize(JTextArea codePane) {
    Font currentFont = codePane.font
    codePane.setFont new Font(currentFont.name, currentFont.style, 18)
}

private def evaluateScript(codePane, frame, slider, label, customPanel) {

    def binding = new Binding()
    binding.putAt("myFrame", frame)
    binding.putAt("mySlider", slider)
    binding.putAt("myLabel", label)
    binding.putAt("customPanel", customPanel)

    def result = ''

    GroovyShell shell = new GroovyShell(binding)
    result = shell.evaluate(codePane.text)

//    codePane.text = "$result"
}

frame.pack()
frame.show()