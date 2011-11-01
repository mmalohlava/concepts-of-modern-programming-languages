(ns collections.test01)
(use 'clojure.repl)

; ===================
; === Collections ===
; ===================

; list and vector
(def testVector [1 2 3])
(def testLongVector [1 2 3 4 5 6 7 8 9 10])
(def testList '(1 2 3))
(def testShortList (list 1 2))
(def testLongList ( quote (1 2 3 4 5 6 7 8 9 10)))

(= testVector testList)

; count function 
; == is for number comparison
; = for object comparison
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
(map + testList testLongVector)
(map + testLongList testLongVector)
(map + testLongList testLongVector testList)
; compare values
(map #(== %1 %2) testList testVector)


; apply
(apply + testList)
; all items are passed as arguments
(apply println testLongList)
; ???? (apply and (map #(== %1 %2) testList testVector))

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
(not-every? #(> 5 %1) testList)
(some #(== 10 %) testLongVector)
(not-any? #(== 0 %) testLongList)

;
; lists
; 
(def countries '("Spain", "Czech Republic", "USA"))
(some #(= % "USA") countries)
(contains? (set testLongList) 10)
; add a new item
(conj testShortList 0)
(cons 0 testShortList)
(remove #(= % 10) testLongVector)
; into 
(into testShortList testLongList)
; more peek, pop treat a list as a stack

;
; sets
;
(def testSet (set [1 2 3 4 5 6 1 2 3 4 5 6]))
(= (count testSet) 6)
(def testSortedSet (sorted-set 10 100 20 30 1 2 3 4 5))

; set can be used as a function
(testSortedSet 100)
(testSortedSet 101)
(#{1 2 3} 3)
(#{1 2 3} 33)


; set operation
(disj testSet testSortedSet)
(union testSet testSortedSet)

(def vowel? (set "aeiou"))
(vowel? (first "abvd"))
(vowel? (first "bvd"))


;
; maps
;
(def testMap {:name "Michal"
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
(= (get testMap :name) (testMap :name) (:name testMap))
(keys testMap)
(vals testMap)
(assoc testMap :address "Praha" :pernament-address "Opava")
(doseq [[key val] (:address testMap) ] (println (str "Key: " (name key) ", Value: " (name val))))

; access inner maps
(get-in testMap [:teaching :lecture :name])
; f1(f2(f3(x)))
(-> testMap :teaching :lecture :sisid)
(reduce get testMap [:teaching :lecture :sisid])






