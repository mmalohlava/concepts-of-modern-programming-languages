(ns hof.test01)

; =============================
; === Higher-order function ===
; =============================

; currying
; x^y
(defn powxy [base exponent] (Math/pow base exponent))
; 2^x
(def pow2 (partial powxy 2))
(pow2 8) ; = 2^8

; polynom derivation - the polynom is declared by its coefficients
(defn derivePoly
  "computes derivation of the polynom"
  [polynom]  
  (let [exponents (reverse (range (count polynom)))]
    (map #(* %1 %2) exponents (butlast polynom))
    )
  )
(derivePoly [2 2 2 3]) ; derive 2x^3 + 2x^2 +2x +3
(time (derivePoly [2 2 2 3]))




