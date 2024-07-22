(ns dr-dustin-tracy.posts.voting-judgement
(:require
 [dr-dustin-tracy.components.blog-posts :as blog-posts]
 [cljs-time.core :as cljs-time]))


(def info {:title "Voting Judgement Pt. 1"
           :description "Analysis of voting methods tend to assume that voters have perfect judgment. As anyone who has interviewed a voter can tell you, this is not the case. This post will explore the implications of this assumption and why it shouldn't be ignored."
           :date (cljs-time/date-time 2024 7 7)
           :url :voting-judgement
           :figure "images/voteman.png"})

(def key-takeaways {:takeaways ["Voters don't have perfect judgment."
                                "This has implications for voting methods."
                                "This is a topic that should be explored more."]})

(defn introduction []
  [blog-posts/blog-paragraph "Have you ever met a voter? If you have, you know that they don't always have the best judgment. This post will explore the implications of this fact on voting methods."])

(defn voting-judgement-panel []
  [:div.bg-slate-900.min-h-screen
   [:div.flex.justify-center.pt-4
   [:div {:class-name "grid cols-1 max-w-6xl"}
    [blog-posts/blog-post-header info]
    [blog-posts/blog-key-takeaways key-takeaways]
    [blog-posts/blog-dislaimer]
    [introduction]]]])

  