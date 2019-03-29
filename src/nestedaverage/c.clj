(ns nestedaverage.c)

; first example, with head recursion

(defn getSum[lis]
  (cond
    (empty? lis) 0

    (number? (first lis))
    (+ (first lis) (getSum (rest lis)))
    :else
    (getSum (rest lis))
    )
  )

(defn getCount[lis]
  (cond
    (empty? lis) 0

    (number? (first lis))
    (+ 1 (getCount (rest lis)))
    :else
    (getCount (rest lis))
    )
  )


(defn getMin
  ([lis]
   (getMin lis Long/MAX_VALUE 0))
  ([lis smallest size]
   (cond
     (and (empty? lis) (= 0 size)) 0
     (empty? lis) smallest

     (number? (first lis))
     (getMin (rest lis) (if (> smallest (first lis)) (first lis) smallest) (inc size))
     :else
     (getMin (rest lis) smallest (inc size))
     )))

(defn getMax
  ([lis]
   (getMax lis Long/MIN_VALUE 0))
  ([lis largest size]
   (cond
     (and (empty? lis) (= 0 size)) 0
     (empty? lis) largest

     (number? (first lis))
     (getMax (rest lis) (if (< largest (first lis)) (first lis) largest) (inc size))
     :else
     (getMax (rest lis) largest (inc size))
     )))

(defn containsNumbers [lis]
  (if (number? (first lis)) true
                            (if (empty? lis) false (containsNumbers (rest lis)))
                            )
  )

(defn myFilter
  ([lis op n]
   (myFilter lis op n '()))
  ([lis op n newList]
   (cond
     (empty? lis) newList
     (number? (first lis))
     (if (op n (first lis))
       (myFilter (rest lis) op n (cons (first lis) newList))
       ( myFilter (rest lis) op n newList))
     :else
     (myFilter (rest lis) op n newList)
     )))

(defn getStatsComparatorHead [n op lis]
  (if (empty? lis)
    nil    ;return nil if empty
    (let [lis (myFilter (flatten lis) op n)       ; filter numbers in list based on the operation passed in args
          smallest (getMin lis)
          largest (getMax lis)
          size (getCount lis)
          sum (getSum lis)]
      (if (containsNumbers lis)                           ; see if there are any numbers in the list...
        {:smallest smallest :largest largest
         :size size :sum sum :average (float  (/ sum size))} -1)) ; return -1 if no numbers found
    )
  )

; second example, with tail recursion

(defn getStatsComparator
  ([n f lis]                                                ; turns list into string then extract numbers from it
   (getStatsComparator n f (map #(Float/parseFloat (% 0)) (re-seq #"\d+(\.\d+)?" (str lis))) 0 0 Long/MAX_VALUE Long/MIN_VALUE))
  ([n f lis sum size smallest largest]
   (cond
     (and (empty? lis) (= 0 size)) nil
     (empty? lis) {:sum sum  :size size :average (float (/ sum size)) :smallest smallest :largest largest}


     (and (number? (first lis)) (f n (first lis)))
     (getStatsComparator n f (rest lis) (+ sum (first lis)) (inc size)
                         (if (< (first lis) smallest ) (first lis) smallest)
                         (if (> (first lis) largest ) (first lis) largest))
     :else
     (getStatsComparator n f (rest lis) sum size smallest largest)
     )))