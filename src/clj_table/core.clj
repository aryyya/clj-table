(ns clj-table.core
  (:gen-class))

(load "table")

(defn get-current-year
  []
  (+ 1900 (.getYear (new java.util.Date))))

(defn get-person
  [character actor born]
  {:character character
   :actor actor
   :born born})

(defn get-age
  [person]
  (- (get-current-year) (:born person)))

(defn sort-by-age
  [people]
  (sort-by (fn [person] (get-age person)) people))

(defn get-people
  []
  [(get-person "Jerry Seinfeld"  "Jerry Seinfeld"      1954)
   (get-person "Larry David"     "Larry David"         1947)
   (get-person "Cosmo Kramer"    "Michael Richards"    1949)
   (get-person "Elaine Benes"    "Julia Louis Dreyfus" 1961)
   (get-person "George Costanza" "Jason Alexander"     1959)
   (get-person "Newman"          "Wayne Knight"        1955)
   (get-person "J. Peterman"     "John O'Hurley"       1954)])

(defn -main
  [& args]
  (println
   (clj-table.table/create
    "Seinfeld Cast (by age)"
    ["Character" "Actor" "Born"]
    (sort-by-age (get-people)))))
