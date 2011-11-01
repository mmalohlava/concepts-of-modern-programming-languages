(ns hof.test02)

(defn fce [f] 
  (partial apply f)
)


(defn apply-fce [f & args]
  (apply f (list args))
)

(apply-fce (fce +) 1 2 3)


(defn myjuxt [& funcs]
  (let [mapa-funkci (map fce funcs)] 
    partial (fn [& args] (map #(apply % (list args)) mapa-funkci ))
  )
 )


( (myjuxt + * max min) 1 2 3)

; ==== TESTS ====
(= [21 6 1] ((myjuxt + max min) 2 3 5 1 6 4))
(= ["HELLO" 5] ((myjuxt #(.toUpperCase %) count) "hello"))
(= [2 6 4] ((myjuxt :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))

; TODO operators ->
