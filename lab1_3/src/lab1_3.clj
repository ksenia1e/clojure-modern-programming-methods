(ns lab1-3
  (:require [clojure.string :refer [split]]))

(defn my-filter [pred coll]
  (reduce 
   (fn [acc x]
     (if (pred x)
       (concat acc (list x))
       acc))
   []
   coll))

(defn my-map [func coll]
  (reduce 
   (fn [acc x]
     (concat acc (list (func x))))
   []
   coll))

(defn validation [s ch]
  (not= (str (last s)) ch))

(defn processing-string [s alphabet]
  (my-map
   (fn [ch]
     (str s ch))
   (my-filter
    (fn [ch]
      (validation s ch))
    alphabet)))

(defn generate-strings [alphabet n res]
  (loop [acc res
         cnt n]
    (if (<= cnt 0)
      acc
      (recur
       (reduce 
        (fn [acc s]
          (concat acc (processing-string s alphabet)))
        []
        acc)
       (dec cnt)))))

(defn -main []
  (println "Введите длину строки (n):")
  (let [n (Integer/parseInt (read-line))]
    (println "Введите буквы через пробел (например: a b c): ")
    (let [dict (split (read-line) #" ")]
      (if (<= n 0)
        (throw (IllegalArgumentException. "\nНеверный ввод данных. Длина строки должна быть больше 0."))
        (do
          (println "\nРезультат генерации:")
          (println (generate-strings dict n [""])))))))

(-main)