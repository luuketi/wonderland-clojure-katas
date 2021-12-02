(ns tiny-maze.solver)

(defn move-to [maze [r c]]
  (for [[idx row] (map-indexed vector maze)]
    (if (= idx r)
      (assoc row c :x)
      row)))

(defn get-pos [maze [r c]]
  (nth (nth maze r) c))

(defn is-valid-next-step? [maze [r c]]
  (and (>= r 0)
       (>= c 0)
       (< r (count maze))
       (< c (count maze))
       (or (= (get-pos maze [r c]) 0)
           (= (get-pos maze [r c]) :E))))

(defn gen-next-steps [r c]
  [[(dec r) c] [r (inc c)] [(inc r) c] [r (dec c)]])

(defn possible-next-steps [maze [r c]]
  (let [next-pos (gen-next-steps r c)]
    (filter #(is-valid-next-step? maze %) next-pos)))

(defn got-exit? [maze]
  (every? true? (for [row maze] (not (contains? (set row) :E)))))

(defn got-no-exit? [maze pos]
  (empty? (possible-next-steps maze pos)))

(defn solve-maze [maze]
    (loop [mazes [(move-to maze [0 0])]
           positions [[0 0]]]
      (let [maze (first mazes)
            pos (first positions)
            next-positions (possible-next-steps maze pos)
            new-mazes (for [np next-positions] (move-to maze np))]
       (cond
        (nil? maze) nil
        (got-exit? maze) maze
        (got-no-exit? maze pos) (recur (rest mazes) (rest positions))
        :else (recur (concat (rest mazes) new-mazes)
                     (concat (rest positions) next-positions))))))
