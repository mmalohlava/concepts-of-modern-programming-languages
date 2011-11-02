(ns meta.test02)

; ===================
; === Hierarchies ===
; ===================

; :key -- keyword (they are not classified with namespace)
; ::key -- resolve fully qualified name of the keyword (with namespace)

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





