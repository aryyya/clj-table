(ns hello-clojure.core
  (:gen-class))

(defn get-current-year
  []
  (+ 1900 (.getYear (new java.util.Date))))

(defn get-person
  [name birth-year]
  {:name name
   :birth-year birth-year})

(defn get-age
  [person]
  (- (get-current-year) (:birth-year person)))

(defn greet
  [person]
  (str (:name person) " was born in " (:birth-year person) " (" (get-age person) " years old)."))

(defn get-people
  []
  [(get-person "Jerry Seinfeld"  1954)
   (get-person "Larry David"     1947)
   (get-person "Cosmo Kramer"    1949)
   (get-person "Elaine Benes"    1961)
   (get-person "George Costanza" 1959)])

(defn sort-by-age
  [people]
  (sort-by (fn [person] (get-age person)) people))

(defn -main
  [& args]
    (println "The characters of Seinfeld, sorted by age:")
    (doall
      (for 
        [person (sort-by-age (get-people))]
        (println (greet person)))))
