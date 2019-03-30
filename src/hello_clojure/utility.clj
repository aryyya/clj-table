(ns hello-clojure.utility)

(defn absolute-value
  [n]
  (max n (- n)))

(defn is-one-off
  [x y]
  (= (absolute-value (- x y))
     1))
