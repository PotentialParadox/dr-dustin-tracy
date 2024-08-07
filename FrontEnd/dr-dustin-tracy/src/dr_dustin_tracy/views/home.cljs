(ns dr-dustin-tracy.views.home
  (:require
   [re-frame.core :as re-frame]
   [dr-dustin-tracy.events :as events]
   [dr-dustin-tracy.posts.voting-judgement :as voting-judgement]
   [dr-dustin-tracy.posts.voting-theory :as voting-theory]
   [dr-dustin-tracy.posts.voting-first-past-the-post :as voting-first-past-the-post]
   [dr-dustin-tracy.components.links :as links]
   [dr-dustin-tracy.components.blog-posts :as blog-posts]))

(defn jumbotron []
  [:div.grid.p-4
   [:div.text-orange-500.text-4xl.lg:text-7xl.flex.justify-center "Welcome!"]
   [:div.text-white.text-lg.lg:text-3xl.flex.text-center.justify-center "This site hosts some of the personal projects of Dustin Tracy."]
   [:div
    [:a {:class-name (str links/a "flex justify-center") :on-click #(re-frame/dispatch [::events/navigate :about])}
     "Learn more about him!"]]])

(defn blog-list []
  [:div.flex.justify-center.pt-4
  [:div.grid.grid-cols-1.max-w-4xl.gap-2
   [blog-posts/list-item voting-judgement/info]
   [blog-posts/list-item voting-theory/info]
   [blog-posts/list-item voting-first-past-the-post/info]]])

(defn home-panel []
;;   (let [name (re-frame/subscribe [::subs/name])]
  [:div.bg-slate-900.min-h-screen
   [jumbotron]
   [blog-list]])
    
