(ns koans.21-partition
  (:require [koan-engine.core :refer :all]))

(meditations
  "To split a collection you can use the partition function"
  (= '((0 1) (2 3)) (partition 2 (range 4)))

  "But watch out if there are not enough elements to form n sequences"
 ;;The incomplete sequence is dropped
  (= '((:a :b :c)) (partition 3 [:a :b :c :d :e]))

  "You can use partition-all to include any leftovers too"
 ;;unless you use partition-all
  (= '((0 1 2) (3 4)) (partition-all 3 (range 5)))

  "If you need to, you can start each sequence with an offset"
 ;;range, then offset. The offset can be < range, then values will be reused
  (= '((0 1 2) (5 6 7) (10 11 12)) (partition 3 5 (range 13)))

  "Consider padding the last sequence with some default values"
 ;;range, offset, padding
  (= '((0 1 2) (3 4 5) (6 :hello)) (partition 3 3 [:hello] (range 7)))

  "But notice that they will only pad up to the given sequence length"
  (= '((0 1 2) (3 4 5) (6 :these :are)) (partition 3 3 [:these :are "my" "words"] (range 7))))
