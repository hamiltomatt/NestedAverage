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

(defn nested-avg-flatten
  ([nlis] (nested-avg-flatten (flatten nlis) '() 0))
  ([nlis used acc]
   (cond
     (empty? nlis) (/ acc (count used))
     :Else
     (recur (rest nlis) (cons (first nlis) used) (+ (first nlis) acc))
     )))

; second example, using head recursion

; Tree based head recursive solution
(defn tree? [lis]
  (and (seq? lis) (not (empty? lis))))

(defn sum-nested [nlis]
  (cond
    (empty? nlis) 0
    (tree? (first nlis)) (+ (sum-nested (first nlis)) (sum-nested (rest nlis)))
    :Else
    (+ (first nlis) (sum-nested (rest nlis)))
    )
  )

(defn count-nested [nlis]
  (cond
    (empty? nlis) 0
    (tree? (first nlis)) (+ (count-nested (first nlis)) (count-nested (rest nlis)))
    :Else
    (+ 1 (count-nested (rest nlis)))
    ))

(defn nested-avg-tree [nlis]
  (let [s (sum-nested nlis) c (count-nested nlis)]
    (if (= c 0)
      0
      (/ (sum-nested nlis) (count-nested nlis))
    )
  )
)

; third example, using tail recursion

(defn nested-averageTail
  ([lis]
   (nested-averageTail (flatten lis) 0 0 0))
  ([lis sum size nums]  ;no 'average' argument as clojure can't divide by 0 which results in errors in first recursive calls where 'sum' and 'num' arguments are both 0 respectively
   (cond

     (and (= 0 size) (nil? (first lis))) 0                ; if the lists are empty
     (and (empty? lis) (= 0 sum)) 0                       ; if no numbers were found in the lists
     (empty? lis) (/ sum nums)                            ;return average


     (number? (first lis))
     (nested-averageTail (rest lis) (+ sum (first lis)) (+ size 1) (+ nums 1))
     :else
     (nested-averageTail (rest lis) sum (+ size 1) nums)
   )
  )
)