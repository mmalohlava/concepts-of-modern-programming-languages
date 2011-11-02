(ns hof.test01)

; =============================
; === Higher-order function ===
; =============================

; currying
; x^y
(defn powxy [base exponent] (Math/pow base exponent))

; 2^x - functions are first class entities !
(def pow2 (partial powxy 2))
(pow2 8) ; = 2^8





