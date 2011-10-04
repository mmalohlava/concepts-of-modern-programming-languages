// Playing around with Subramaniam's Pizza DSL (see the DSL chapter in "Programming Groovy")
// The example
// - uses enums for size and ingredient = typed, validated input
//   no static import needed to access the enum values while omitting class name
// - sets an attribute map via a "with()", "size()" and "to()" method
// - evaluates duration and price dynamically on printing of the instance
/**
 * @author http://groovyconsole.appspot.com/edit/549001
 */





import groovy.swing.SwingBuilder
import java.awt.Font
import javax.swing.JTextArea

def createOrder(String data) {
    new GroovyShell().evaluate("""
    Pizza.order {
        $data
    }
    """)
}





SwingBuilder builder = new SwingBuilder()
def frame
def customPanel
frame = builder.frame(title: 'Pizza to your door!') {
    vbox {
        scrollPane {
            def codePane = textArea(id: 'codePane', columns: 60, rows: 20)
            setFontSize(codePane)
        }
        hbox {
            customPanel = panel() {
                button('The daily choice of the chef', actionPerformed: {
                        codePane.text = '''size large
with onions, cheese, mushrooms
to "kancelar JetBrains"
                        '''
                })
                button('Order', actionPerformed: {
                    doOutside {
                        def order = createOrder(codePane.text)
                        println order
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

frame.pack()
frame.show()

























