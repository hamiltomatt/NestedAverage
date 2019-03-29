(ns nestedaverage.b-test
  (:require [clojure.test :refer :all]
            [nestedaverage.b :refer :all]))

;tests for first example
(deftest b-test
  (is (= (stats_flat '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) {:smallest 1, :largest 50, :size 10, :sum 180, :average 18}))
)
(deftest b-test2
  (is (= (get (stats_flat '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) :average) 18))
)
(deftest b-test3
  (is (= (stats_flat '(0)) {:smallest 0, :largest 0, :size 1, :sum 0, :average 0}))
)
(deftest b-test4
  (is (= (stats_flat '(1)) {:smallest 1, :largest 1, :size 1, :sum 1, :average 1}))
)
(deftest b-test5
  (is (= (get (stats_flat '(0))  :average) 0))
)
(deftest b-test6
  (is (= (stats_flat '()) nil))
)

;tests for third example
(deftest b-tailtest1
  (is (= (nested-averageTail '("d" "g" :a)) nil))
)
(deftest b-tailtest2
  (is (= (nested-averageTail '("d " 2 "g" :a)) {:smallestN 2, :biggestN 2, :numbers 1, :size 4, :sum 2, :average 2}))
)
(deftest b-tailtest3
  (is (= (nested-averageTail '()) 0))
)
(deftest b-tailtest4
  (is (= (nested-averageTail '(((([]))))) 0))
)
(deftest b-tailtest5
  (is (= (nested-averageTail '("d " 1 5 6 :a 0)) {:smallestN 0, :biggestN 6, :numbers 4, :size 6, :sum 12, :average 3}))
)
(deftest b-tailtest6
  (is (= (nested-averageTail '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) {:smallestN 1, :biggestN 50, :numbers 10, :size 10, :sum 180, :average 18}))
)

(run-tests)