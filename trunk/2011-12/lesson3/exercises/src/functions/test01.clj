(ns functions.test01)
  (use 'clojure.repl)
; for debugging (use 'clojure.contrib.trace) - 

; =========================
; === Clojure functions ===
; =========================


; forward declaration of a function
(declare computeCircleArea) 

; define a function
(defn computeCircleArea 
  "Compute Circle Area" ; documentation = meta information
  ; test can be also a meta-information
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
  (reduce * (range 1 (inc n)))
  )
(factorial 5)

; function can have multiple bodies according to number of parameters
(defn hello
  ([] "hello world")
  ([name] (str "hello " name))
  ([name caller] (str caller " say: " (hello name)))
  )
(hello ) 
(hello "LISP WORLD")
(hello "LISP WORLD" 'CLOJURE)
(hello "LISP WORLD" :clojure)

; memoize
(defn f [n] (pow2 n))
(def memo-f (memoize f))

(println "without memoization")
; Note the use of an underscore for the binding that isn't used.
(dotimes [_ 3] (time (f 2)))

(println "with memoization")
(dotimes [_ 3] (time (memo-f 2)))

; -------------------------

; -- TODO SHOW ho to test 
; function meta-data and tests
(defn fibnum
  "compute n-th fibionacciho number"
  { ; meta-data
   :version "1.0"
   :static true
   :doc "barakadabre"
   }
  [n] ; parameter
  (2)
  )
