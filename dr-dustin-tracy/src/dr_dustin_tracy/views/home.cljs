(ns dr-dustin-tracy.views.home
  (:require
   [re-frame.core :as re-frame]
   [dr-dustin-tracy.events :as events]
   [dr-dustin-tracy.posts.voting-judgement :as voting-judgement]
   [dr-dustin-tracy.components.links :as links]
   [dr-dustin-tracy.components.blog-posts :as blog-posts]))

(defn jumbotron []
  [:div.grid
   [:div.text-orange-500.text-7xl.flex.justify-center "Welcome!"]
   [:div.text-white.text-3xl.flex.text-center.justify-center "This site hosts some of the personal projects of Dustin Tracy."]
   [:div
    [:a {:class-name (str links/a "flex justify-center") :on-click #(re-frame/dispatch [::events/navigate :about])}
     "Learn more about him!"]]])

(defn blog-list []
  [:div.grid.grid-rows-subgrid.row-span-3.max-w-2xl
   [:div.row-start-2
    [blog-posts/list-item voting-judgement/info]]])

(defn home-panel []
;;   (let [name (re-frame/subscribe [::subs/name])]
  [:div.bg-slate-900.h-screen
   [jumbotron]
   [blog-list]])
    
