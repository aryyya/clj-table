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

(defn create
  [columns records]
  "Here is your table!")
