(ns hof.test02)

; =================
; Assignment - implement a function myjuxt which implements function juxt
(clojure.repl/doc juxt)

(= [21 6 1] ((myjuxt + max min) 2 3 5 1 6 4))
(= ["HELLO" 5] ((myjuxt #(.toUpperCase %) count) "hello"))
(= [2 6 4] ((myjuxt :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))

