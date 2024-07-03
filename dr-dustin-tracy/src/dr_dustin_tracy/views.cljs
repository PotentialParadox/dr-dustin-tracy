(ns dr-dustin-tracy.views
  (:require
   [dr-dustin-tracy.routes :as routes]
   [dr-dustin-tracy.views.main :as main]
   [dr-dustin-tracy.views.home :as home]
   [dr-dustin-tracy.views.about :as about]
   ))


(def home-panel home/home-panel)
(defmethod routes/panels :home-panel [] [home-panel])

(def about-panel about/about-panel)
(defmethod routes/panels :about-panel [] [about-panel])

(def main-panel main/main-panel)