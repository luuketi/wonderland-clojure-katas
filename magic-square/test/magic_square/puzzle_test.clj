(ns magic-square.puzzle-test
  (:require [clojure.test :refer :all]))
(require '[magic-square.puzzle :as msp])

(defn sum-rows [m]
  (map #(reduce + %) m))

(defn sum-cols [m]
  [(reduce + (map first m))
   (reduce + (map second m))
   (reduce + (map last m))])

(defn sum-diagonals [m]
  [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
   (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])

(deftest test-magic-square
  (testing "all the rows, columns, and diagonal add to the same number"
    (is (= (set (sum-rows (msp/magic-square msp/values)))
           (set (sum-cols (msp/magic-square msp/values)))
           (set (sum-diagonals (msp/magic-square msp/values)))))

    (is (= 1
           (count (set (sum-rows (msp/magic-square msp/values))))
           (count (set (sum-cols (msp/magic-square msp/values))))
           (count (set (sum-diagonals (msp/magic-square msp/values))))))))
