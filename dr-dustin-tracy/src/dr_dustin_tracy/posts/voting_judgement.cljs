(ns dr-dustin-tracy.posts.voting-judgement
(:require
 [cljs-time.core :as cljs-time]))


(def info {:title "Voting Judgement Pt. 1"
           :description "Analysis of voting methods alway assume that voters have perfect judgment. As anyone who has interviewed a voter can tell you, this is not the case. This post will explore the implications of this assumption."
           :date (cljs-time/date-time 2024 7 7)
           :figure "images/voteman.png"})