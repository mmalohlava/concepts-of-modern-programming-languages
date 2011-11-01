(ns meta.test01)

; ==============
; === Macros ===
; ==============

; wrong version of unless
(defn unless [condition body] (if (not condition) body))
(unless true (println "This text should not be printed"))
(unless false (println "This is the text which the user can see"))
                                                                                                                                   
(macroexpand meta)
(macroexpand 'meta)
(macroexpand ''something)
(macroexpand `meta)
(macroexpand `~meta)

(macroexpand '#(* %1 2)) ; macroexpansion allows to treat code as a list
; If you don’t want a function to execute right away, quote it.
; will replace the arguments intact. Our unless will look like this:

; redefine unless
(defmacro unless 
  ([condition body ] (list 'if (list 'not condition) body))
  ([condition body else-body] `(if (not ~condition) ~body ~else-body))
  )

(macroexpand '(unless cond body else))
(macroexpand-1 '(unless cond body else))

(unless true (println "AHOJ") (println "ELSE_AHOJ"))
(unless false (println "AHOJ") (println "ELSE_AHOJ"))


(defmacro resolution [] `x)
(macroexpand '(resolution))
(def x 100)
(let [x 109] (resolution))




