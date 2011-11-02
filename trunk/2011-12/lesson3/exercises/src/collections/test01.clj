(ns collections.test01
 (:use [clojure.repl :only [doc source]]))
  

; ===================
; === Collections ===
; ===================

; list and vector
(def testVector [1 2 3])
(def testLongVector [1 2 3 4 5 6 7 8 9 10])
(def testList '(1 2 3))
(def testLongList ( quote (1 2 3 4 5 6 7 8 9 10)))

; count function 
(== (count '(1)) 1)
(== (count testVector) 3)
(== (count testList) 3)

; 
; Manipulators
;

; reverse function
(reverse testVector)
(reverse testList)

; map
(= (map #(* 2 %) testVector) (map #(* 2 %1) testList))
(map + testVector testList)
(map + testVector testLongList)
(map + testLongList testLongVector testList)

; compare values
(map #(== %1 %2) testList testVector)


; apply
(apply + testList)
; all items are passed as arguments
(apply println testLongList)

; items access
(first testLongVector)
(last testLongVector)
(nth testLongVector 3)

(next testLongList)

(butlast testLongList)

(filter #(> %1 5) testLongList)

(drop-last 3 testLongList)

(nthnext testLongList 3)

;
; Tests
;
; Tests on condition
(every? #(number? %1) testLongList) 

(not-every? #(string? %1) testLongList)

(not-every? #(> 5 %1) testLongList)

(some #(== 10 %) testLongVector)

(not-any? #(== 0 %) testLongList)

;
; lists
; 
(def countries '("Spain", "Czech Republic", "USA"))

(some #(= % "USA") countries)

; add a new item
(conj countries "Germany")

(cons "Germany" countries)

; do not swap function parameters
(cons countries "Germany") ; the second parameter is a sequence. String is implicitly converted to a sequence of characters

(remove #(= % "USA") countries)

; into 
(into testList testLongList)
; more peek, pop treat a list as a stack

;
; sets
;
(def testSet (set [1 2 3 4 5 6 1 2 3 4 5 6]))

(= (count testSet) 6)

(def testSortedSet (sorted-set 10 100 20 30 1 2 3 4 5))

; set can be used as a function
(testSortedSet 100)

(testSortedSet 101) ; nil and false do not represent truth

; #{} syntax is used to define a set
(#{1 2 3} 3)
(#{1 2 3} 33)


; set operation
(clojure.set/intersection testSet testSortedSet)
(clojure.set/union testSet testSortedSet)
(clojure.set/difference testSet testSortedSet)

; a set used as a function again
(def vowel? (set "aeiou"))
(vowel? (first "abvd"))
(vowel? (first "bvd"))


;
; maps
;
(def SIS-item {:name "Michal"
              :surname "Malohlava"
              :address "Opava"
              :teaching {
                         :lecture {
                                   :name "Concepts of modern programming languages"
                                   :sisid "NPRG014"
                                   }
                         
                         } 
              }
  )

; field access
(= (get SIS-item :name) (SIS-item :name) (:name SIS-item))

(keys SIS-item)

(vals SIS-item)

; add a new item into a map
(assoc SIS-item :address "Praha" :pernament-address "Opava")

(doseq [[key val] SIS-item ] 
  (println 
    (str "Key: " 
         (name key) 
         ", Value: " 
         (if (string? val) (name val) val))))

; access inner maps
(get-in SIS-item [:teaching :lecture :sisid])

; f1(f2(f3(x)))
(-> SIS-item :teaching :lecture :sisid)

(reduce get SIS-item [:teaching :lecture :sisid])

; ================
; Assignment: write a function which finds indexes of a given element
(== '(5) (pos 3 [:a 1 :b 2 :c 3]))
(== '(:c) (pos 3 {:a 1, :b 2, :c 3}))
(== '(1 3 5) (pos 3 [:a 3 :b 3 :c 3]))







