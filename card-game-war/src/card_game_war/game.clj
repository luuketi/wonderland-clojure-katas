(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round [player1-card player2-card]
    (if (> (.indexOf cards player1-card) (.indexOf cards player2-card))
      :player1-wins
      :player2-wins))

(defn play-game [player1-cards player2-cards]
  (cond
    (and (empty? player1-cards) (empty? player2-cards)) :draw
    (empty? player1-cards) :player2-wins
    (empty? player2-cards) :player1-wins
    :else (let [player1-card (first player1-cards)
                player2-card (first player2-cards)
                winner (play-round player1-card player2-card)]
            (cond
              (= winner :player1-wins) (play-game (conj (rest player1-cards) player1-card player2-card)
                                                  (rest player2-cards))
              (= winner :player2-wins) (play-game (rest player1-cards)
                                                  (conj (rest player2-cards) player1-card player2-card))))))
