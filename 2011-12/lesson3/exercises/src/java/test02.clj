(ns interop.test02)

; ================
; === Java SWT ===
; ================

(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

; example taken from http://en.wikibooks.org/wiki/Clojure_Programming/Examples/Temperature_Converter_GUI
 
(let [frame (new JFrame "Celsius Converter")
      temp-text (new JTextField)
      celsius-label (new JLabel "Celsius")
      convert-button (new JButton "Convert")
      fahrenheit-label (new JLabel "Fahrenheit")]
    (. convert-button
        (addActionListener
           (proxy [ActionListener] []
                (actionPerformed [evt]
                    (let [c (Double/parseDouble (. temp-text (getText)))]
                      (. fahrenheit-label
                         (setText (str (+ 32 (* 1.8 c)) " Fahrenheit"))))))))
    (doto frame 
                ;(.setDefaultCloseOperation (JFrame/EXIT_ON_CLOSE)) 
                (.setLayout (new GridLayout 2 2 3 3))
                (.add temp-text)
                (.add celsius-label)
                (.add convert-button)
                (.add fahrenheit-label)       
                (.setSize 300 80)
                (.setVisible true)))

; ==============
; Assignment: add a new button for closing the application