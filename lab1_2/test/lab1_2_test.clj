(ns lab1-2-test
  (:require [clojure.test :refer [deftest is testing]]
            [lab1-2 :refer [-main generate-strings]]))

(deftest -main-test
  (testing "Обработка неверного ввода длины строки (число <= 0)"
    (is (thrown? IllegalArgumentException (with-in-str "0\na b" (-main)))))

  (testing "Успешное выполнение"
    (testing "Длина строки 2:"
      (let [output (with-out-str (with-in-str "1\na b c"
                                   (-main)))]
        (is (re-find #"Результат генерации:" output))
        (is (re-find #"a" output))
        (is (re-find #"b" output))
        (is (re-find #"c" output))))))

(deftest generate-strings-test
  (testing "Длина строки=2, набор символов=[a b c]"
    (is (= '("ab" "ac" "ba" "bc" "ca" "cb") (generate-strings ["a" "b" "c"] 2 [""]))))

  (testing "Длина строки=3, набор символов=[a b]"
    (is (= '("aba" "bab") (generate-strings ["a" "b"] 3 [""])))))