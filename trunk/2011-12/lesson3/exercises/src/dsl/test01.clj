(ns dsl.test01)

(defmacro ^{:tag-A "testovaci tag"} objednej 
  "ahoj dokumentace"
  [& polozky] `(println str "pocet polozek v poli " '~polozky "je"  (count '~polozky) " a k tomu" ~@polozky))

(macroexpand '(objednej :a :b :c :d :e))
(objednej :a :b :c)

; ============= meta data =================
(^{:muj-tag "nejaka metadata"} objednej)
(with-meta 'objednej {:muj-tag2 "nejaka metadata navic"})
(meta #'objednej)

; ========= pre -post conditions ====
(defn test [x]
  {
    :pre [(> x 10)]
    :post [(< % 15)]
  }
  x)

(test (* 3 6))

(defmacro chci
  [& polozky] `())

; =============== === ===============
; =============== DSL ===============
; =============== === ===============
; EXAMPLE (chci (pizza with tomato and fish))
(take 20 (range))

(def demonstrate-math-op (juxt + - * /))
(demonstrate-math-op 1 2)




