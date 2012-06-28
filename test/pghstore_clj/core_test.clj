(ns pghstore-clj.core-test
  (:use clojure.test
        pghstore-clj.core))

(deftest test-hash-to-hstore
  (testing "creating a PGobject with the correct value"
    (is (= "hstore" (.getType (hstore {:color "blue"})))))
  (testing "storing the correct value in the PGobject"
    (is (= "\"name\"=>\"test\", \"username\"=>\"root\""
           (.getValue (hstore {:name "test" :username "root"}))))))