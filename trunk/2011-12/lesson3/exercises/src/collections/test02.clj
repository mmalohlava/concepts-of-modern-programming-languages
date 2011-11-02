(ns collections.test02)

; =================
; === Sequences ===
; =================

; sequence generators
(range 1 21)
(range 1 21 3)


; infinite seq
; (repeat 2) ; infinite sequence ! does not stop !
(take 3 (repeat [1 2]))

(take 7 (drop 2 (cycle '(3 2 1 0))))

(take 8 (iterate #(* % 2) 1)) ; iterate f(f(...f(x)) ..))

; operator 
(->> [:hi :hello] (cycle) (drop 1) (take 8))

; insertion into a sequence
(->> [:hi :hello] (cycle) (drop 1) (interpose :and) (map #(name %)) (take 8))

; interleaving of sequences
(take 10 (interleave (cycle [:jedna :dve :tri]) (cycle [:my :jsme :bratri])))

; it is possible to take any item of the sequence
(nth (interleave (cycle [:jedna :dve :tri]) (cycle [:my :jsme :bratri])) 1000)
(nth (interleave (cycle [:jedna :dve :tri]) (cycle [:my :jsme :bratri])) 1001)
(nth (interleave (cycle [:jedna :dve :tri]) (cycle [:my :jsme :bratri])) 1002)

; ====================
; Assignment: generates a sequence of n Fibionaci numbers 

(defn fib [n] (
                )
  )

