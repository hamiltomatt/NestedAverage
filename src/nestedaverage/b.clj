(ns nestedaverage.b)

; first example, using flattened list

(defn getSum [lis]
  (if (empty? lis)
    0
    (+ (first lis) (getSum (rest lis)))
    )
  )

(defn stats [lis]
  (if (empty? lis)
    nil    ;return nil if empty
    (let [lis (flatten lis)
          smallest (apply min lis)
          largest (apply max lis)
          size (count lis)
          sum (getSum lis)
          average (/ sum size)]
      {:smallest smallest :largest largest
       :size size :sum sum :average average})
    )
  )

;tests
(stats '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40))
(get (stats '(10 ((30 1) 20) (8 (5 (50 7)) 9) 40))  :average)
(stats '(1))
(stats '(0))
(get (stats '(0))  :average)
(stats '())