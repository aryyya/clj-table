(ns table)
(require 'utility)

(defn left-pad
  [string width]
  (if (< (count string) width)
      (left-pad (str string " ") width)
      string))

(defn right-pad
  [string width]
  (if (< (count string) width)
      (right-pad (str " " string) width)
      string))

(defn center-pad
  [string width]
  (if (< (count string) width)
      (if (utility/is-one-off (count string) width)
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
             (merge widths {column-key (apply max (get-column-lengths column-key records))})))
    {}
    columns))

(defn create
  [title columns records]
  (println (get-column-max-lengths columns records))
  "Here is your table!")
