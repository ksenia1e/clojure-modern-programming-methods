(ns lab1-2
  (:require [clojure.string :refer [split]]))

(defn processing-single-letter [letter dict res]
  (loop [items dict
         acc res]
    (if (empty? items)
      acc
      (recur (rest items)
             (if (not= (last letter) (first (first items)))
               (conj acc (str letter (first items)))
               acc)))))

(defn processing-all-strings [dict1 dict2 res]
  (loop [items dict1
         acc res]
    (if (empty? items)
      acc
      (recur (rest items)
             (processing-single-letter (first items)
                                       dict2
                                       acc)))))

(defn generate-strings [dict n res]
  (loop [cnt n
         acc res]
    (if (<= cnt 0)
      acc
      (recur (- cnt 1) (processing-all-strings acc dict [])))))

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