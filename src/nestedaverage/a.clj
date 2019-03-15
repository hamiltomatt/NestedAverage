(ns nestedaverage.a)

; first example, using flattened list

(defn getSum [lis]
  (if (empty? lis)
    0
    (+ (first lis) (getSum (rest lis)))
    )
  )

(defn nested-average [lis]
  (let [lis (flatten lis)
        size (count lis)
        sum (getSum lis)]
    (if (empty? lis)
      nil
      ;return the average in integer or float
      (let [result (/ sum size)] (if (integer? result) result (float result)))
      )
    )
  )

; tests
(nested-average '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40))
(nested-average '(10 ((30.2 1.34) 20.8) (8 (5 (50 7)) 9) 40))
(nested-average '(1))
(nested-average '(0))
(nested-average '())