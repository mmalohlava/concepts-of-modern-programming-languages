(ns basics.test01)

; ======================
; === Clojure basics ===
; ======================

; This is a comment

; 
; s-expression: (a b c)
;  - (...) is a list
;  - symbol is a b c
;  - Clojure tries to evaluate a list considering a as function/macro/special form and b, c its parameters

; form is a Clojure object to be evaluated, e.g., list, vectors, numbers, keywords
; special form is a form with special syntax or evaluation rule (e.g, dot operator, def, defn, ...)

(== 6 (* 2 3)) 
  
(= "abc" (str "a" "b" "c"))

; keyword = symbolic identifier (its value is itself)
(= (keyword "hello") :hello)

; define a list
(list 1 2 3) ; evaluates items => (list a 2 3) does not work

; quoting
'(1 "two" 3 (list 1 2 3)) ; does not evaluate any items
'(1 (* 2 3))

; syntactic quoting `
`(1 (* 2 3))
; unquoting
`(1 ~(* 2 3)) ; ~ permits evaluation
`(1 ~@'(* 2 3)) ; ~@ unpacks the list


; list of keywords 
'(:red :green :blue) 

; define a global BINDING (my-list is immutable object)
(def my-list '(a b c))

; use let for local bindings (hide global def)
(let [x 1 y x] (+ x y))
; (println x) ; does not work because x is not defined

; keywords
(def RGB '(:red :cyan :blue))
(= '(:red :green :blue) RGB)

; ==============================
; Assignment: correct definition of RGB using already defined RGB list
; Be aware that all dat are immutable! Use functions first and nth, or replace
; (= '(:red :green :blue) (list ____) )


