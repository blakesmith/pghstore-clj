(ns pghstore-clj.core
  (:require [clojure.string :as st])
  (:import [org.postgresql.util PGobject]))

(defprotocol THstorable
  (to-hstore [this]))

(defprotocol FHstorable
  (from-hstore [this]))

(extend-type clojure.lang.PersistentArrayMap
  THstorable
  (to-hstore [this]
    (doto (PGobject.)
      (.setType "hstore")
      (.setValue
       (apply str
              (interpose ", "
                         (for [[k v] this]
                           (format "\"%s\"=>\"%s\"" (name k) (name v)))))))))

(extend-type org.postgresql.util.PGobject
  FHstorable
  (from-hstore [this]
    (into {}
          (for [[k v]
                (map (fn [v]
                       (map #(st/replace % #"\"" "") (st/split v #"=>")))
                     (st/split (.getValue this) #", "))]
            [(keyword k) v]))))