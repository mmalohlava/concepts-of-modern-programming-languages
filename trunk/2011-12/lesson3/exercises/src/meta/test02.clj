(ns meta.test02)

; ===================
; === Hierarchies ===
; ===================

; :key -- keyword
; ::key -- resolve fully qualified name of the keyword
; fully qualified name is required
(derive ::rect ::shape)
(derive ::triangle ::shape)
(derive ::square ::rect)
; try to do cyclic hierarchy: 
; (derive ::rect ::square)

(parents ::rect)
(parents ::square)
(ancestors ::square)
(descendants ::shape)

; child-parent relation?
(isa? ::rect ::shape)
(isa? ::shape ::shape)
(isa? ::shape ::triangle)

; query Java hierarchy
(isa? String Object)
(isa? java.util.Map String)
(ancestors java.util.HashMap)

; multi-methods via Maps
(defmulti area :Shape)
(defn rect [wd ht] {:Shape :Rect :wd wd :ht ht})
(defn circle [radius] {:Shape :Circle :radius radius})
(defmethod area :Rect [r]
    (* (:wd r) (:ht r))) ; map element is used as a function !
(defmethod area :Circle [c]
    (* (. Math PI) (* (:radius c) (:radius c))))
(defmethod area :default [x] :oops)
(def r (rect 4 13))
(def c (circle 12))

; TODO: contextual eval - Joy of Clojure - p160

(use '[clojure.xml :as xml])
(xml/emit ::rect)


