(ns aoc2018-2)

;; 파트 1
;; 주어진 각각의 문자열에서, 같은 문자가 두번 혹은 세번씩 나타난다면 각각을 한번씩 센다.
;; 두번 나타난 문자가 있는 문자열의 수 * 세번 나타난 문자가 있는 문자열의 수를 반환하시오.
;; 예)
;; abcdef 어떤 문자도 두번 혹은 세번 나타나지 않음 -> (두번 나오는 문자열 수: 0, 세번 나오는 문자열 수: 0)
;; bababc 2개의 a, 3개의 b -> (두번 나오는 문자열 수: 1, 세번 나오는 문자열 수: 1)
;; abbcde 2개의 b -> (두번 나오는 문자열 수: 2, 세번 나오는 문자열 수: 1)
;; abcccd 3개의 c -> (두번 나오는 문자열 수: 2, 세번 나오는 문자열 수: 2)
;; aabcdd 2개의 a, 2개의 d 이지만, 한 문자열에서 같은 갯수는 한번만 카운트함 -> (두번 나오는 문자열 수: 3, 세번 나오는 문자열 수: 2)
;; abcdee 2개의 e -> (두번 나오는 문자열 수: 4, 세번 나오는 문자열 수: 2)
;; ababab 3개의 a, 3개의 b 지만 한 문자열에서 같은 갯수는 한번만 카운트함 -> (두번 나오는 문자열 수: 4, 세번 나오는 문자열 수: 3)
;; 답 : 4 * 3 = 12


;(vals (frequencies (seq (char-array "bababc"))))
(defn num-contains? [n coll]
  (if (some #(= % n) coll)
    1
    0))

(def contains-two? (partial num-contains? 2))
(def contains-three? (partial num-contains? 3))

(num-contains? 3 '(2 1 4 5 6 3))

(def multiply (->> "resources/aoc2018-2_1.sample.txt"
                   (slurp)
                   (clojure.string/split-lines)
                   (map frequencies)                        ; ({\a 1, \b 1, \c 1, \d 1, \e 1, \f 1} {\b 3, \a 2, \c 1}....)
                   (map vals)                               ; ((1 1 1 1 1 1) (3 2 1) ...)
                   (map (fn [freq-vals]
                          ((juxt contains-two? contains-three?) freq-vals)))
                   (apply map +)
                   (apply *)
                   ))

multiply

;; 파트 2
;; 여러개의 문자열 중, 같은 위치에 정확히 하나의 문자가 다른 문자열 쌍에서 같은 부분만을 리턴하시오.
;; 예)
;; abcde
;; fghij
;; klmno
;; pqrst
;; fguij
;; axcye
;; wvxyz

;; 주어진 예시에서 fguij와 fghij는 같은 위치 (2번째 인덱스)에 정확히 한 문자 (u와 h)가 다름. 따라서 같은 부분인 fgij를 리턴하면 됨.

(compare "abc" "abd")
(compare "aaf" "ahf")
(compare "abc" "abc")
(compare ["a" "a" "c"] ["a" "h" "c"])                       ; -7 (a -> h) 7 step
(= (map-indexed #(when (= 2 %2) [%1 "Hi"]) [1 1 2 2])
   '(nil nil [2 "Hi"] [3 "Hi"]))                            ;;=>true

(map-indexed vector ["a" "h" "c"])

;(def set-a ["a" "b" "c" "d"])
;(def set-b ["a" "h" "c" "d"])
(def set-a "fghij")
(def set-b "fguij")
(diff set-a set-b)

(def input-string (->> "resources/aoc2018-2_2.sample.txt"
                                (slurp)
                                (clojure.string/split-lines)))

input-string
(def combine-strings (for [str-list1 input-string]
                       (for [str-list2 input-string]
                         [str-list1 str-list2])))

combine-strings



(two-sum [2 7 11 15] 9)
;(defn find-pattern [source]
;  (reduce
;    (fn [{prev    :prev
;          results :results} input]
;      (if (results input)
;        (reduced input)
;        (do
;          (prn "result : " results)
;          (prn "prv" prev "input" input)
;          (prn "result last" (last (diff prev input)))
;          (def compare-val (last (diff prev input)))
;          {:prev    input
;           :results (conj results compare-val)}))
;      )
;
;    source))
;
;(defn make-diff-set [str-list]
;  (reduce
;    (fn [results input]
;      (last (diff (first results) input))) #{} str-list))
;
;(def another-one-character (->> "resources/aoc2018-2_2.sample.txt"
;                                (slurp)
;                                (clojure.string/split-lines)  ;["abcde" "fghij" "klmno" "pqrst" "fguij" "axcye" "wvxyz"]
;                                #_(map (fn [str-list]
;                                       (loop [str str-list]
;                                         (seq (char-array str))))) ;((\a \b \c \d \e) (\f \g \h \i \j) (\k \l \m \n \o) (\p \q \r \s \t) (\f \g \u \i \j) (\a \x \c \y \e) (\w \v \x \y \z))
;                                ;(reductions (conj #{}))
;                                ;(find-pattern)
;                                ;(map-indexed vector)
;                                ))
;
;another-one-character



;; #################################
;; ###        Refactoring        ###
;; #################################
