(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= :player1-wins (play-round [:spade 9] [:spade 5])))
    (is (= :player2-wins (play-round [:spade 4] [:spade 6]))))
  (testing "queens are higher rank than jacks"
    (is (= :player2-wins (play-round [:spade :jacks] [:spade :queen]))))
  (testing "kings are higher rank than queens"
    (is (= :player1-wins (play-round [:spade :king] [:spade :queen]))))
  (testing "aces are higher rank than kings"
    (is (= :player2-wins (play-round [:spade :king] [:spade :ace]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= :player1-wins (play-round [:club :king] [:spade :king]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= :player2-wins (play-round [:club :king] [:diamond :king]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= :player1-wins (play-round [:heart :king] [:diamond :king])))))


(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= :player1-wins (play-game [[:heart 3]] [])))
    (is (= :player2-wins (play-game [] [[:diamond :ace]]))))
  (testing "if both players run out of cards then it is a draw"
    (is (= :draw (play-game [] []))))
  (testing "player2 wins"
    (is (= :player2-wins (play-game [[:heart 2] [:diamond 6]] [[:heart 5] [:club :king]]))))
  (testing "player1 wins"
    (is (= :player1-wins (play-game [[:heart 2] [:diamond 6]]
                                    [[:diamond :king] [:club :king]])))))
