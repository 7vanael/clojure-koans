(ns koans.14-recursion
  (:require [koan-engine.core :refer :all]))

(defn is-even? [n]
  (if (= n 0)
    true
    (not (is-even? (dec n)))))

(defn is-even-bigint? [n]
  (loop [n   n
         even-tracker true]
    (if (= n 0)
      even-tracker
      (recur (dec n) (not even-tracker)))))

(defn recursive-reverse [coll]
  (loop [reversed () coll coll]
    (if (empty? coll)
    reversed
      ;;This next part uses the fact that conj will add
      ;;the first item into the front of the reversed collection
      ;;then continues with everything except the first item in the collection
      ;;() (1 2 3) => (1) (2 3) => (2 1)  (3) => (3 2 1) ()
    (recur (conj reversed (first coll)) (rest coll)))))

(defn factorial [n]
  (loop [n n acc 1]
    (if (= 1 n)
      acc
      (recur (dec n) (* n acc)))))

(meditations
  "Recursion ends with a base case"
  (= true (is-even? 0))

  "And starts by moving toward that base case"
  (= false (is-even? 1))

  "Having too many stack frames requires explicit tail calls with recur"
  (= false (is-even-bigint? 100003N))

  "Reversing directions is easy when you have not gone far"
  (= '(1) (recursive-reverse [1]))

  "Yet it becomes more difficult the more steps you take"
  (= '(6 5 4 3 2) (recursive-reverse [2 3 4 5 6]))

  "Simple things may appear simple"
  (= 1 (factorial 1))

  "They may require other simple steps"
  (= 2 (factorial 2))

  "Sometimes a slightly bigger step is necessary"
  (= 6 (factorial 3))

  "And eventually you must think harder"
  (= 24 (factorial 4))

  "You can even deal with very large numbers"
  (< 1000000000000000000000000N (factorial 1000N))

  "But what happens when the machine limits you?"
  (< 1000000000000000000000000N (factorial 100003N)))
