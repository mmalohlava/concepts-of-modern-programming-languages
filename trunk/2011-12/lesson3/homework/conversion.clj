(ns nprg014.conversion)

; Write a DSL which allows users to define conversion rules specified in the following form: 
(defunits-of distance :m ; rules for distance, the base unit is :m (one meter)
  :km 1000
  :cm 1/100 ; Clojure allows rationals
  :mm 1000
  :ft 0.3048
  :mile 1 609 )
  
; The conversion rules are called distance and the base unit is :m representing 1 meter. 
; A conversion rule for given unit defines a relation of the unit to meters (e.g., 1 km is 1000 m). 

; The macro should prepare a new function/macro units-of-distance which allow users to convert given value to given unit 
; For example:
(unit-of-distance 1 :km) ; shoudl return 1000
(unit-of-distance 2 :cm) ; should return 2/100


