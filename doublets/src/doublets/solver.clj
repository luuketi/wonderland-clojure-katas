(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)
               (set)))


(defn is-valid-word [word]
  (contains? words word))


(defn change-one-pos [word position]
  (defn replace-at [s idx replacement]
    (str (subs s 0 idx) replacement (subs s (inc idx))))
  (for [letter "abcdefghijklmnopqrstuvwxyz"
        :let [new-word (replace-at word position letter)]
        :when (and (not= word new-word) (is-valid-word new-word))]
    new-word))


(defn generate-words [word]
  (for [idx (range (count word))
        :let [new-words (change-one-pos word idx)]
        :when (not (empty? new-words))]
    new-words))


(defn doublets [word1 word2]

  (defn create-doublet [word1 word2]
    (defn create-combinations [combinations]
      (apply concat (for [comb combinations
                          :let [new-words (generate-words (last comb))
                                words-as-list (for [nw new-words] (concat comb nw))]]
                      words-as-list)))
    (defn find-doublet [combinations]
      (flatten (for [c combinations :when (= word2 (last c))] c)))

    (loop [combinations (generate-words word1)]
      (let [new-combinations (create-combinations combinations)
            list-with-word2 (find-doublet new-combinations)]
        (if (not (empty? list-with-word2))
          list-with-word2
          (recur new-combinations)))))

  (if (= (count word1) (count word2))
    (concat [word1] (create-doublet word1 word2))
    ()))
