(ns nestedaverage.c-test
    (:require   [clojure.test :refer :all]
                [nestedaverage.c :refer :all]))

(deftest c-headtest1 ; only get stats from numbers above 20
  (is (= (getStatsComparatorHead 20 < '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) {:smallest 30, :largest 50, :size 3, :sum 120, :average 40.0}))
)
(deftest c-headtest2 ; only get stats from numbers in list below 5
  (is (= (getStatsComparatorHead 5 > '(5 7 9 34 125 62 61 2 0 4)) {:smallest 0, :largest 4, :size 3, :sum 6, :average 2.0}))
)
(deftest c-headtest3 ; only take stats from numbers in list equal to or greater than 10
  (is (= (getStatsComparatorHead 10 <= '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) {:smallest 10, :largest 50, :size 5, :sum 150, :average 30.0}))
)
(deftest c-headtest4 ; only get stats from numbers in list below or equal to 10
  (is (= (getStatsComparatorHead 10 > '(10 :a ((30 1) 20) '(:a) [{:a a :b b}] (8 (5 (50 7)) 9) :b 40)) {:smallest 1, :largest 9, :size 5, :sum 30, :average 6.0}))
)
(deftest c-headtest5 ; return nil if no numbers were found
  (is (= (getStatsComparatorHead 10 > '()) nil))
)
(deftest c-headtest6 ; return -1 if no numbers were found
  (is (= (getStatsComparatorHead 6.6 > '(a)) -1))
)

(deftest c-tailtest1 ;only get stats from numbers in list above 20
  (is (= (getStatsComparator 20 < '(5 7 9 34 125 62 61 2 0 4)) {:sum 282.0, :size 4, :average 70.5, :smallest 34.0, :largest 125.0}))
)
(deftest c-tailtest2 ; only  get stats from numbers in list below 5
  (is (= (getStatsComparator 5 > '(5 7 9 34 125 62 61 2 0 4)) {:sum 6.0, :size 3, :average 2.0, :smallest 0.0, :largest 4.0}))
)
(deftest c-tailtest3 ; only take stats from numbers in list equal to or greater than 10
  (is (= (getStatsComparator 10 <= '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40)) {:sum 150.0, :size 5, :average 30.0, :smallest 10.0, :largest 50.0}))
)
(deftest c-tailtest4 ; only get stats from numbers in list below or equal to 10
  (is (= (getStatsComparator 10 > '(10 :a ((30 1) 20) '(:a) [{:a a :b b}] (8 (5 (50 7)) 9) :b 40)) {:sum 30.0, :size 5, :average 6.0, :smallest 1.0, :largest 9.0}))
)
(deftest c-tailtest5 ; return nil if no numbers were found and the size is 0
  (is (= (getStatsComparator 10 > '()) nil))
)

(run-tests)