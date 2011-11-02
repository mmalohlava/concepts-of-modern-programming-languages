(ns meta.test03)

; ==============
; === Macros ===
; ==============

; wrong version of unless
(defn unless [condition body] (if (not condition) body))
(unless true (println "This text should not be printed"))
(unless false (println "This is the text which the user can see"))

; redefine unless with help of macro
(defmacro unless 
  ([condition body ] (list 'if (list 'not condition) body))
  )

(macroexpand '(unless cond body))

(unless true (println "This text will not be printed"))

; note about arguments expansion
(defmacro resolution [] `x)
(macroexpand '(resolution))
(def x 100)
(let [x 109] (resolution))

; ==================
; Assignment: modify macro unless to have else branch
;(unless true (println "Unless true") (println "Else branch"))




