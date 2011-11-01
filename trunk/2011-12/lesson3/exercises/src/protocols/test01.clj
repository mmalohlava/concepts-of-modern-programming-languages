(ns protocols.test01)

; =================
; === Protocols ===
; =================

; ~ polymorphism of protocols
(defprotocol Concatenatable
  (cat [this other]))

(extend-type String
  Concatenatable
    (cat [this other]
         (.concat this other)))

(cat "House" " of Leaves")


(extend-type java.util.List
  Concatenatable
    (cat [this other]
         (concat this other)))

(cat [1 2 3] [4 5 6 7])


