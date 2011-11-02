(ns meta.test01)

; =============================
; === Clojure multi methods ===
; =============================

; class based dispatch
(= String (class "This is a string"))
(defmulti f class)
(defmethod f String [arg] (println "Argument type is string"))
(defmethod f Number [arg] (println "Argument type is number"))
(defmethod f :default [arg] (println "Argument type is" (class arg)))

(f "foo")
(f 10)
(f :jey)

; multimethod's dispatch function
(defn crazy-dispatch [_] (if (== (rand-int 2) 1)
                                 :say-truth
                                 :say-lie
                            )
  )

(defmulti lier crazy-dispatch)
(defmethod lier :say-truth [arg] (println "Argument " arg "is a " (class arg)))
(defmethod lier :say-lie [arg] (println "Argument " arg "is a String"))
(defmethod lier :default [arg] (println "Argument " arg "is a" (class arg)))

(lier "X")
(lier 1)
(lier :blue)
(lier :blue)

; =================
; Assignment: Create a multi method h that dispatches on the value of the argument being in an interval <1, 100>

; debug (clojure.repl/pst *e) ; *e refers last exception
