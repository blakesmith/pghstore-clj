(ns pghstore-clj.core-test
  (:use clojure.test
        pghstore-clj.core))

(deftest test-hash-to-hstore
  (testing "creating a PGobject with the correct value"
    (is (= "hstore" (.getType (to-hstore {:color "blue"})))))
  (testing "storing the correct value in the PGobject"
    (is (= "\"name\"=>\"test\", \"username\"=>\"root\""
           (.getValue (to-hstore {:name "test" :username "root"}))))))

(deftest test-hstore-to-hash
  (let [pgo (to-hstore {:name "test" :username "root"})]
    (testing "creating a hash from a PGobject"
      (is (= {:name "test" :username "root"} (from-hstore pgo))))))
    