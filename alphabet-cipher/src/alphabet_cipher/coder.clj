(ns alphabet-cipher.coder)

(def rotations [["abcdefghijklmnopqrstuvwxyz"],
                ["bcdefghijklmnopqrstuvwxyza"],
                ["cdefghijklmnopqrstuvwxyzab"],
                ["defghijklmnopqrstuvwxyzabc"],
                ["efghijklmnopqrstuvwxyzabcd"],
                ["fghijklmnopqrstuvwxyzabcde"],
                ["ghijklmnopqrstuvwxyzabcdef"],
                ["hijklmnopqrstuvwxyzabcdefg"],
                ["ijklmnopqrstuvwxyzabcdefgh"],
                ["jklmnopqrstuvwxyzabcdefghi"],
                ["klmnopqrstuvwxyzabcdefghij"],
                ["lmnopqrstuvwxyzabcdefghijk"],
                ["mnopqrstuvwxyzabcdefghijkl"],
                ["nopqrstuvwxyzabcdefghijklm"],
                ["opqrstuvwxyzabcdefghijklmn"],
                ["pqrstuvwxyzabcdefghijklmno"],
                ["qrstuvwxyzabcdefghijklmnop"],
                ["rstuvwxyzabcdefghijklmnopq"],
                ["stuvwxyzabcdefghijklmnopqr"],
                ["tuvwxyzabcdefghijklmnopqrs"],
                ["uvwxyzabcdefghijklmnopqrst"],
                ["vwxyzabcdefghijklmnopqrstu"],
                ["wxyzabcdefghijklmnopqrstuv"],
                ["xyzabcdefghijklmnopqrstuvw"],
                ["yzabcdefghijklmnopqrstuvwx"],
                ["zabcdefghijklmnopqrstuvwxy"]])

(defn char-to-pos [char]
  (- (int char) (int \a)))

(defn encode [keyword message]
  (defn translate [key letter]
    (let [row (first (nth rotations (char-to-pos letter)))
          col (nth row (char-to-pos key))]
      col))
  (apply str (map translate (cycle keyword) message)))

(defn decode [keyword message]
  (defn translate [key letter]
    (let [row (first (nth rotations (char-to-pos letter)))
          col (nth row (rem (inc (- (int \z) (int key))) 26))]
      col))
  (apply str (map translate (cycle keyword) message)))

(defn decipher [cipher message]
  (defn translate [key letter]
    (let [row (first (nth rotations (char-to-pos key)))
          secrets (nth row (rem (inc (- (int \z) (int letter))) 26))
          size (count secrets)]
      size
      ))
  (apply str (map translate cipher message)))

