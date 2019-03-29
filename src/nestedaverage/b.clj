(ns nestedaverage.b)

; first example, using flattened list

(defn getSum [lis]
  (if (empty? lis)
    0
    (+ (first lis) (getSum (rest lis)))
    )
  )

(defn stats_flat [lis]
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

; second example, using head recursion

; Tree based head recursive solution
(defn tree? [lis]
  (and (seq? lis) (not (empty? lis))))

(defn count-nested [nlis]
  (cond
    (empty? nlis) 0
    (tree? (first nlis)) (+ (count-nested (first nlis)) (count-nested (rest nlis)))
    :Else
    (+ 1 (count-nested (rest nlis)))
    ))

(defn smallest-nested
  "Finds the smallest item in a list"
  ([lis] (smallest-nested lis (first lis)))
  ([lis cur]
   (cond
     (empty? lis) cur
     (tree? (first lis)) (let [smallest-tree (smallest-nested (first lis))]
                           (cond
                             (< smallest-tree cur) (recur (rest lis) smallest-tree)
                             :else
                             (recur (rest lis) cur)))
     (< (first lis) cur) (recur (rest lis) (first lis))
     :else
     (recur (rest lis) cur))
    )
  )

(defn greatest-nested
  "Finds the smallest item in a list"
  ([lis] (greatest-nested lis (first lis)))
  ([lis cur]
   (cond
     (empty? lis) cur
     (tree? (first lis)) (let [greatest-tree (greatest-nested (first lis))]
                           (cond
                             (> greatest-tree cur) (recur (rest lis) greatest-tree)
                             :else
                             (recur (rest lis) cur)))
     (> (first lis) cur) (recur (rest lis) (first lis))
     :else
     (recur (rest lis) cur))
    )
  )

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
  (/ (sum-nested nlis) (count-nested nlis)))

(defn stats
  "Gets stats about a nested list"
  [lis] (hash-map :smallest (smallest-nested lis)
                  :largest (greatest-nested lis)
                  :count (count-nested lis)
                  :average (nested-avg-tree lis)))

; third example, using tail recursion

(defn nested-averageTail
  ([lis]
   (nested-averageTail (flatten lis) 0 0 0 Long/MAX_VALUE Long/MIN_VALUE))
  ([lis sum size nums smallest biggest]  ;no 'average' argument as clojure can't divide by 0 which results in errors in first recursive calls where 'sum' and 'num' arguments are both 0 respectively
   (cond

     (and (= 0 size) (nil? (first lis))) 0                ; if the lists are empty => 0
     (and (empty? lis) (= 0 sum)) nil                       ; if no numbers were found in the lists => nil
     ;   (empty? lis) (/ sum nums)                            ;return average only
     (empty? lis) {:smallestN smallest :biggestN biggest :numbers nums :size size :sum sum :average (/ sum nums)} ; return stats

     (number? (first lis))
     (nested-averageTail (rest lis) (+ sum (first lis)) (+ size 1) (+ nums 1)
                         (if (> smallest (first lis)) (first lis) smallest) ;getting smallest
                         (if (< biggest (first lis)) (first lis) biggest) ;getting biggest
                         )
     :else
     (nested-averageTail (rest lis) sum (+ size 1) nums smallest biggest)
   )
  )
)