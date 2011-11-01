(ns meta.test03)

; =============================
; === Clojure multi methods ===
; =============================

; class based dispatch
(= String (class "This is a string"))

; TODO normal class-based dispatch
;


; (class "X"); (class class); (class :key); (class 'neco); class {}; (class ()); (class #{})
; multimethods, specifies dispatch method
(defn crazy-dispatch [_] (if (== (rand-int 2) 1)
                                 :say-truth
                                 :say-lie
                            )
  )

(defmulti lier crazy-dispatch)
(defmethod lier :say-truth [arg] (println arg "is a " (class arg)))
(defmethod lier :say-lie [arg] (println arg "is a String"))
(defmethod lier :default [arg] (println arg "is a" (class arg)))

;(dotrace [lier] (lier "X")) ; requires clojure.contrib namespace
(lier "X")
(lier 1)
(lier :blue)
(lier :blue)

