(ns basics.test01)

; ======================
; === Clojure basics ===
; ======================

; This is a comment

(= "abc" (str "a" "b" "c"))
(= (keyword "hello") :hello)

; define a list
(list 1 2 3) ; evaluates items
'(1 2 3) ; does not evaluate items
(:red :green :blue) ; list of keywords 

; vector
[1 2 3]
(= '(1 2 3) [1 2 3])

; = is a method call
(= (list 1 2 3) '(1 2 3) '(1, 2, 3))

; define a global BINDING (my-list is immutable object)
(def my-list '(a b c))
; use let for local bindings (hide global def)
(def my-value (let [x 1 y x] y)) 
(= my-value 1)
; more bindings (thread-local) will be introduced later 

; define a FUNCTION 
(defn print-list [l] (println (concat "This is a list: " l)))
(print-list my-variable)

; define a method with two parameters
(defn print-two-lists [l1 l2] (println (concat "List1: " l1 ", List 2:" l2))) 
(print-two-lists muj-list muj-list)

; define anonymous function
(map (fn [x] (* 2 x)) '(1 2 3))
(map #(* 2 %1) '(2 3 4))

; keywords
(def RGB '(:red :blue :cyan))
(= (:red :green :blue) RGB)

; Assignment: correct definition of RGB using already defined RGB list
; be aware that all dat are immutable
;(def RGB '( ~(first RGB) ~(second RGB) :green)) ; tohle fungovat nebude, protoze ' neevaluju vnitrek listu
;(def RGB `( ~(first RGB) ~(second RGB) :green)) ; quoting is reguired, because ' does not evaluate internals, OR list has to be used
; (def RGB (list (first RGB) (second RGB) :green))
(= (:red :green :blue) RGB)

; TODO basics structures
; IF, FOR, WHILE
