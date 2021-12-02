(ns magic-square.puzzle)
(require '[clojure.math.combinatorics :as combo])

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn sum-rows [m]
  (map #(reduce + %) m))

(defn sum-cols [m]
  [(reduce + (map first m))
   (reduce + (map second m))
   (reduce + (map last m))])

(defn sum-diagonals [m]
  [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
   (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])


(defn magic-square [values]

  (defn make-matrix [perm]
    [(subvec perm 0 3) (subvec perm 3 6) (subvec perm 6 9)])
  (defn is-valid? [matrix]
    (let [set-rows (set (sum-rows matrix))
          set-cols (set (sum-cols matrix))
          set-diagonals (set (sum-diagonals matrix))]
      (and
        (= set-rows set-cols set-diagonals)
        (= 1 (count set-rows) (count set-cols) (count set-diagonals)))))

  (first (filter is-valid? (map make-matrix (combo/permutations values)))))
