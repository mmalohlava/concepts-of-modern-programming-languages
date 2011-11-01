(ns collections.test03)

; ======================
; === Structures     ===
; ======================


; structure
(defstruct person-struct :name :surname :address :tel)
(defstruct address-struct :street :psc :city)
(def person-michal (struct person-struct
                           "michal" 
                           "malohlava"
                           (struct address-struct "ulice" "10000" "prague")))
(person-michal :name)
(-> person-michal :address :psc)

; or faster access via creating a method based on accessor  
(def address-psc (accessor address-struct :psc))
(def person-address (accessor person-struct :address))

; (comp f g) is a combined function f*g 
(def psc (comp address-psc person-address))
; psc is HOF (higher-order function)
(psc person-michal)


