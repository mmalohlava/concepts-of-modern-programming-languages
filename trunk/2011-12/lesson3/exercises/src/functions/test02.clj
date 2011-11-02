(ns functions.test01)

; =========================
; === Clojure functions ===
; =========================

; ===============================

; memoize
(defn f [n] (reduce * (range 1 (inc n))))
(def memo-f (memoize f))

(println "without memoization")
; Note the use of an underscore for the binding that isn't used.
(dotimes [_ 3] (time (f 20)))

(println "with memoization")
(dotimes [_ 3] (time (memo-f 20)))

; ===============================

; tail recursion
(def pow2 #(Math/pow 2 %))

(defn sum-squares [initial-n] 
  (loop [sum 0 n initial-n] ; <- recur target
    (if (pos? n)
      (recur (+ sum (pow2 n)) (dec n))
      sum)  
   )
)
(sum-squares 2)
   

; ===============================
; Assignment: write a function which recursively evaluate a polynom given by its coeficients
; e.g, [a b c] represents ax^2 + bx + c, which can be evaulated as x*(ax + b) + c 
(= (evaluate-poly [1 1 1] 3) 13)