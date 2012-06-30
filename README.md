# pghstore-clj

## What is it?

pghstore-clj is a small helper library to help you work with PostgresSQL's hstore data type with the postgresql JDBC adapter (I use version 9.1-901.jdbc4). It provides two simple helpers to help you get your data into and out of a hash-map.

## Installation

Add pghstore-clj to your project.clj file in leiningen:

  [pghstore-clj "0.1.0"]

## Usage

Once installed, you can use it like so:

  user> (use '[pghstore-clj.core])
  nil                                                                                                                                                                            35user> (def h (to-hstore {:color "blue" :size "small"}))
  #'user/h                                                                                                                                                                       user> h
  #<PGobject "color"=>"blue", "size"=>"small">                                                                                                                                   user> (from-hstore h)
  {:color "blue", :size "small"}                                                                                                                                                 user> 

## [Korma](http://sqlkorma.com/) example

If you have a table named "products" with an hstore column called "attributes", a korma insertion statement might look like this:

  (insert products
    (values {:name "computer"
             :attributes (to-hstore {:color "black" :manufacturer "samsung"})}))

When you pull the row back out again, just call "from-hstore" on the value at attributes.

  user> row
  {:name "computer", :attributes #<PGobject "color"=>"black", "manufacturer"=>"samsung">}                                                                                        user> (from-hstore (:attributes row))
  {:color "black", :manufacturer "samsung"}                                                                                                                                      user> 

## Wishlist

Ideally, these transformation functions would be applied at the JDBC level, so that you only have to work with hashes - This is going to take a bit more work.

## License

Copyright © 2012 Blake Smith

Distributed under the Eclipse Public License, the same as Clojure.

