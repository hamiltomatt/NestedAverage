(ns nestedaverage.a-test
  (:require [clojure.test :refer :all]
            [nestedaverage.a :refer :all]))

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
(run-tests)