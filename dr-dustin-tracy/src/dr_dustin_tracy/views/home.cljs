(ns dr-dustin-tracy.views.home
  (:require
   [re-frame.core :as re-frame]
   [dr-dustin-tracy.events :as events]
   [dr-dustin-tracy.components.links :as links]
   [dr-dustin-tracy.components.headers :as headers]))

(defn jumbotron []
  [:div.grid.grid-rows-subgrid.row-span-3
   [:div.row-start-2
    [:div {:class-name (str headers/h1 "flex justify-center")} "Welcome to Dustin Tracy's personal project page!"]
    [:div
     [:a {:class-name (str links/a "flex justify-center") :on-click #(re-frame/dispatch [::events/navigate :about])}
      "Learn more about him!"]]]])

(defn blog-list []
  [:div.grid.grid-rows-subgrid.row-span-3
   [:div.row-start-2 
    [:div {:class-name headers/h1} "Blog 1"]]])

(defn home-panel []
;;   (let [name (re-frame/subscribe [::subs/name])]
  [:div.grid.grid-cols-2.bg-black.h-screen
   [jumbotron]
   [blog-list]])
    
