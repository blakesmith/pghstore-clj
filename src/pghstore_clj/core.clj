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
                         (map (fn [m]
                                (let [[k v] m]
                                  (format "\"%s\"=>\"%s\"" (name k) (name v))))
                              this)))))))
                         