(ns dr-dustin-tracy.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [dr-dustin-tracy.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 1))))
