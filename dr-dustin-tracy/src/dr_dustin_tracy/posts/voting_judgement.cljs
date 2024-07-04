(ns dr-dustin-tracy.posts.voting-judgement
(:require
 [cljs-time.core :as cljs-time]))


(def info {:title "Voting Judgement Pt. 1"
           :description "Analysis of voting methods tend to assume that voters have perfect judgment. As anyone who has interviewed a voter can tell you, this is not the case. This post will explore the implications of this assumption and why it shouldn't be ignored."
           :date (cljs-time/date-time 2024 7 7)
           :figure "images/voteman.png"})