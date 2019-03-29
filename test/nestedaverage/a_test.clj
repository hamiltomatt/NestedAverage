(ns nestedaverage.a-test
  (:require [clojure.test :refer :all]
            [nestedaverage.a :refer :all]))

; tests for first example
(deftest a-test
  (is (= (nested-average '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) 18))
)
(deftest a-test2
  (is (= (nested-average '(1)) 1))
)
(deftest a-test3
  (is (= (nested-average '(0)) 0))
)
(deftest a-test4
  (is (= (nested-average  '()) nil))
)
(deftest a-test5                                                                ;fails as cannot eval longs
  (is (= (nested-average '(10 ((30.2 1.34) 20.8) (8 (5 (50 7)) 9) 40)) 18.134))
)

; tests for third example
(deftest a-tailtest1
  (is (= (nested-averageTail '("d" "g" :a)) 0))
)
(deftest a-tailtest2
  (is (= (nested-averageTail '("d " 2 "g" :a)) 2))
)
(deftest a-tailtest3
  (is (= (nested-averageTail '()) 0))
)
(deftest a-tailtest4
  (is (= (nested-averageTail '(((([]))))) 0))
)
(deftest a-tailtest5
  (is (= (nested-averageTail '("d " 1 5 6 :a 0)) 3))
)
(deftest a-tailtest6
  (is (= (nested-averageTail '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) 18))
)
(run-tests)