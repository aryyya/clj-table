(ns hello-clojure.core
  (:gen-class))
(require 'table)

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

(defn repeat1
  [c n]
  (if (= n 1)
      (str c "\n")
      (str c (repeat1 c (- n 1)))))

(defn get-header
  [column-names]
  (str
    (reduce
      (fn [columns column]
          (if (= columns "")
              (format "%-16s" column)
              (str columns " | " (format "%-16s" column))))
      ""
      column-names)
    "\n"))

(defn get-row
  [map]
  (str
    (reduce-kv
      (fn [m k v]
          (if (= m "")
              (format "%-16s" v)
              (str m " | " (format "%-16s" v))))
      ""
      map)
    "\n"))

(defn get-table
  [title column-names records]
  (str
    title "\n"
    (repeat1 "-" 41)
    (get-header column-names)
    (repeat1 "-" 41)
    (reduce
      (fn [rows row]
        (str rows (get-row row)))
        ""
      records)))

(defn show-table
  []
  (println
    (get-table
      "Seinfeld Cast"
      ["Character" "Actor" "Age"]
      [{:character "George Costanza" :actor "Jason Alexander"  :age 25}
       {:character "Jerry Seinfeld"  :actor "Jerry Seinfeld"   :age 26}
       {:character "Cosmo Kramer"    :actor "Michael Richards" :age 27}])))

(defn -main
  [& args]
    (println "The characters of Seinfeld, sorted by age:")
    (doall
      (for 
        [person (sort-by-age (get-people))]
        (println (greet person)))))
