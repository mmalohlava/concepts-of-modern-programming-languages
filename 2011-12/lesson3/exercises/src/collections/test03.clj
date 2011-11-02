(ns collections.test03)

; ======================
; === Structures     ===
; ======================

; structure
(defstruct person-struct :name :surname :address :tel)
(defstruct address-struct :street :psc :city)

(def person-franta (struct person-struct
                           "Franta" 
                           "Pudil"
                           (struct address-struct "Na Belidle" "15000" "Prague")))

(person-franta :name)

(-> person-franta :address :psc)

; or faster access via creating a method based on struct accessor  
(def address-psc (accessor address-struct :psc))
(def person-address (accessor person-struct :address))

; (comp f g) is a combined function f*g 
(def psc (comp address-psc person-address))

; psc is HOF (higher-order function)
(psc person-franta)

; ====
(def psc-slow #(-> % :address :psc))
(psc-slow person-franta)

; compare times
(defn stopwatch [f iterations] (time (dotimes [_ iterations] (f person-franta))))

(stopwatch psc 10000)
(stopwatch psc-slow 10000)
