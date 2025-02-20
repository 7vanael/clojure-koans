(ns koans.20-java-interop
  (:require [koan-engine.core :refer :all]))

(meditations
  "You may have done more with Java than you know"
  (= String (class "warfare")) ; hint: try typing (javadoc "warfare") in the REPL

  "The dot signifies easy and direct Java interoperation"
  (= "SELECT * FROM" (.toUpperCase "select * from"))

 ;;Java methods aren't "first class" functions, so they need to be wrapped in a function
 ;;This method passes a function and a vector to the map function
  "But instance method calls are very different from normal functions"
  (= ["SELECT" "FROM" "WHERE"] (map #(.toUpperCase %) ["select" "from" "where"]))

 ;;I guessed 10, but couldn't tell you what that java function is except it has to do with
 ;;threading, which I also don't know how to do yet.
  "Constructing might be harder than breaking"
  (= 10 (let [latch (java.util.concurrent.CountDownLatch. 10)]
          (.getCount latch)))

  "Static methods are slashing prices!"
  (== 1024 (Math/pow 2 10)))
