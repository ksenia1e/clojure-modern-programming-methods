(ns lab1-1
  (:require [clojure.string :refer [split]]))

(defn processing-single-letter [letter dict res]
  (if (empty? dict)
    res
    (if (not= (last letter) (first (first dict)))
      (processing-single-letter letter
                                (rest dict)
                                (conj res (str letter (first dict))))
      (processing-single-letter letter
                                (rest dict)
                                res))))

(defn processing-all-strings [dict1 dict2 res]
  (if (empty? dict1)
    res
    (processing-all-strings (rest dict1)
                            dict2
                            (processing-single-letter (first dict1)
                                                      dict2
                                                      res))))

(defn generate-strings [dict n res]
  (if (<= n 0)
    res
    (generate-strings dict
                      (dec n)
                      (processing-all-strings res dict []))))

(defn -main []
  (println "Введите длину строки (n):")
  (let [n (Integer/parseInt (read-line))]
    (println "Введите буквы через пробел (например: a b c): ")
    (let [dict (split (read-line) #" ")]
      (if (<= n 0)
        (throw (IllegalArgumentException. "\nНеверный ввод данных. Длина строки должна быть больше 0."))
        (do
          (println "\nРезультат генерации:")
          (println (generate-strings dict n [""]))))
      )
  )
)

(-main)