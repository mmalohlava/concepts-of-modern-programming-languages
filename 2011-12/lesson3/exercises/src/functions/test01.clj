(ns functions.test01)
  (use 'clojure.repl)
; for debugging (use 'clojure.contrib.trace) - 

; =========================
; === Clojure functions ===
; =========================


; define a function 
(defn print-list [list] (println (str "This is a list: " list)))
(print-list (quote (1 2 3))) ; quote is a synonymum for '

; define a method with two parameters
(defn print-two-lists [l1 l2] (println (str "List1: " l1 ", List 2: " l2))) 
(print-two-lists '(:a :b :c) [1 2 3])

; define and use anonymous function
((fn [x] 
   (* 2 x)) 3)

; or special #() reader form
(#(* 2 %1) 4) ; functions parameters are referenced via %1 %2 


; forward declaration of a function
(declare computeCircleArea) 

; define a function
(defn computeCircleArea 
  "Compute Circle Area" ; documentation = meta information
  [r] ; parameters
  (* Math/PI r r) ; access to Java static class Math
  )

(computeCircleArea 2)

; shows meta-information of the function
(meta #'computeCircleArea)

; operator & in variable list means variable number of parameters - they are gathered as a list
(defn power [base & exponents]
  (reduce #(Math/pow %1 %2) base exponents))

(power 2 3 4 ) ; =2^3^4

; factorial
(defn factorial [n] 
  (reduce * (range 1 (inc n))); means 1*2*3*4...*(n+1)
  )

(factorial 20)

; function can have multiple bodies according to number of parameters
(defn hello
  ([] "hello everybody")
  ([name] (str "hello " name))
  ([name caller] (str caller " say: " (hello name)))
  )
(hello ) 
(hello "Lisp world")
(hello "Lisp world" 'Clojure)
(hello "Lisp world" (name :clojure))

