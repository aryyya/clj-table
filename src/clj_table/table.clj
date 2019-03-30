(ns clj-table.table)
(load "utility")

(defn repeat-character
  [character n]
  (if (> n 1)
    (str character (repeat-character character (- n 1)))
    character))

(defn left-pad
  [string width]
  (if (< (count (str string)) width)
    (left-pad (str string " ") width)
    string))

(defn right-pad
  [string width]
  (if (< (count (str string)) width)
    (right-pad (str " " string) width)
    string))

(defn center-pad
  [string width]
  (if (< (count (str string)) width)
    (if (clj-table.utility/is-one-off (count (str string)) width)
      (left-pad string width)
      (center-pad (str " " string " ") width))
    string))

(defn get-keyword
  [string]
  (keyword (clojure.string/lower-case string)))

(defn get-column-lengths
  [column-key records]
  (reduce
   (fn [columns record]
     (conj columns
           (count (str (column-key record)))))
   [(count (name column-key))]
   records))

(defn get-column-max-lengths
  [columns records]
  (reduce
   (fn [widths column]
     (let [column-key (get-keyword column)]
       (merge widths
              {column-key (+ 3 (apply max (get-column-lengths column-key records)))})))
   {}
   columns))

(defn get-table-length
  [column-max-lengths]
  (reduce-kv
   (fn [table-length column-key column-length]
     (+ column-length table-length))
   0
   column-max-lengths))

(defn get-header
  [columns column-max-lengths]
  (reduce
    (fn [output column]
      (str output (left-pad column ((keyword (clojure.string/lower-case column)) column-max-lengths))))
    ""
    columns))

(defn get-row
  [columns column-max-lengths record]
  (str
   (reduce
    (fn [output column]
      (let [column-key (keyword (clojure.string/lower-case column))]
        (str
         output
         (left-pad (column-key record)
                   (column-key column-max-lengths)))))
    ""
    columns)
   "\n"))

(defn get-rows
  [columns column-max-lengths records]
  (reduce
   (fn [output record]
     (str output (get-row columns column-max-lengths record)))
   ""
   records))

(defn create
  [title columns records]
  (let [column-max-lengths (get-column-max-lengths columns records)
        table-length (get-table-length column-max-lengths)]
    (str
     (repeat-character "-" table-length)
     "\n"
     (center-pad (clojure.string/upper-case title) table-length)
     "\n"
     (repeat-character "-" table-length)
     "\n"
     (get-header columns column-max-lengths)
     "\n"
     (repeat-character "-" table-length)
     "\n"
     (get-rows columns column-max-lengths records)
     (repeat-character "-" table-length))))
