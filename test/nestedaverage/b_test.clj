(ns nestedaverage.b-test
  (:require [clojure.test :refer :all]
            [nestedaverage.b :refer :all]))

;tests for first example
(deftest b-test
  (is (= (stats '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) {:smallest 1, :largest 50, :size 10, :sum 180, :average 18}))
)
(deftest b-test2
  (is (= (get (stats '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) :average) 18))
)
(deftest b-test3
  (is (= (stats '(0)) {:smallest 0, :largest 0, :size 1, :sum 0, :average 0}))
)
(deftest b-test4
  (is (= (stats '(1)) {:smallest 1, :largest 1, :size 1, :sum 1, :average 1}))
)
(deftest b-test5
  (is (= (get (stats '(0))  :average) 0))
)
(deftest b-test6
  (is (= (stats '()) nil))
)

;tests for third example
(deftest b-tailtest1
  (is (= (nested-averageTail '("d" "g" :a)) 0))
)
(deftest b-tailtest2
  (is (= (nested-averageTail '("d " 2 "g" :a)) 2))
)
(deftest b-tailtest3
  (is (= (nested-averageTail '()) 0))
)
(deftest b-tailtest4
  (is (= (nested-averageTail '(((([]))))) 0))
)
(deftest b-tailtest5
  (is (= (nested-averageTail '("d " 1 5 6 :a 0)) 3))
)
(deftest b-tailtest6
  (is (= (nested-averageTail '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) 18))
)

(run-tests)