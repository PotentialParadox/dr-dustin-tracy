(ns dr-dustin-tracy.views
  (:require
   [dr-dustin-tracy.routes :as routes]
   [dr-dustin-tracy.views.main :as main]
   [dr-dustin-tracy.views.home :as home]
   [dr-dustin-tracy.views.about :as about]
   [dr-dustin-tracy.posts.voting-judgement :as voting-judgement]
   ))


(def home-panel home/home-panel)
(defmethod routes/panels :home-panel [] [home-panel])

(def about-panel about/about-panel)
(defmethod routes/panels :about-panel [] [about-panel])

(def voting-judgement-panel voting-judgement/voting-judgement-panel)
(defmethod routes/panels :voting-judgement-panel [] [voting-judgement-panel])

(def main-panel main/main-panel)