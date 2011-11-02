(ns nprg014.conversion)

; write a DSL which allows users to define conversion rules
(defunits-of distance :m ; rules for distance, the base unit is :m (one meter)
  :km 1000
  :cm 1/100 ; Clojure allows rationals
  :mm 1000
  :ft 0.3048
  :mile 1 609 )

; which prepares function/macro units-of-distance which allow users to convert given value to given unit 
; For example:
(unit-of-distance 1 :km) ; shoudl return 1000
(unit-of-distance 2 :cm) ; should return 2/100


