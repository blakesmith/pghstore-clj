(ns pghstore-clj.core
  (:import [org.postgresql.util PGobject]))

(defprotocol Hstorable
  (hstore [this]))

(extend-type clojure.lang.PersistentArrayMap
  Hstorable
  (hstore [this]
    (doto (PGobject.)
      (.setType "hstore")
      (.setValue
       (apply str
              (interpose ", "
                         (for [[k v] this]
                           (format "\"%s\"=>\"%s\"" (name k) (name v)))))))))

                         