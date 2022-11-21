(ns basic)

(defn FunctionExample []
  ((fn [x] (* 2 x)) 2))
(FunctionExample)
(println "======")
;(defn demo [] (* 2 2))
;(defn demo [x] (* 2 x))
(defn demo [x y] (* 2 x y))
(demo 3 4)
(println "======")
(filter even? (range 0 10))
(println "======")
(filter odd? (range 0 10))
(println "======")
(defn Loop-Example []
  (loop [i 0]
    (when (< i 5)
      (println i)
      (recur (inc i)))))
(Loop-Example)
(println "======")
(defn IOExample []
  (def string1 (slurp "resources/day3.sample.txt"))
  (println string1))
(IOExample)
(println "======")
(defn IOLineExample []
  (with-open [rdr (clojure.java.io/reader "resources/day3.sample.txt")]
    (reduce conj [] (line-seq rdr))))
(IOLineExample)
(println "======")
(defn PredicateExample []
  (def x (even? 0))
  (println x)

  (def x (neg? 2))
  (println x)

  (def x (odd? 3))
  (println x)

  (def x (pos? 3))
  (println x))
(PredicateExample)
(println "======")
(defn DestructingExample []
  (def my-vector [1 2 3 4])
  (let [[a b c d] my-vector]
    (println a b c d))
  (def my-vector [1 2 3 4])
  (let [[a b c d e] my-vector]
    (println a b c d e))
  )
  (def my-vector [1 2 3 4])
  (let [[a b & the-rest] my-vector]
    (println a b the-rest))
  (def my-map {"a" 1 "b" 2})
  (let [{a "a" b "b"} my-map]
    (println a b))
  (def my-map {"a" 1 "b" 2})
  (let [{a "a" b "b" c "c"} my-map]
    (println a b c))

(DestructingExample)
(println "======")

