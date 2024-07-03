(ns dr-dustin-tracy.views.home
  (:require
   [re-frame.core :as re-frame]
   [breaking-point.core :as bp]
   [dr-dustin-tracy.events :as events]
   [dr-dustin-tracy.subs :as subs]
   ))

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      (str "Hello from " @name ". This is the Home Page.")]

     [:div
      [:a {:on-click #(re-frame/dispatch [::events/navigate :about])}
       "go to About Page"]]
     [:div
      [:h3 (str "screen-width: " @(re-frame/subscribe [::bp/screen-width]))]
      [:h3 (str "screen: " @(re-frame/subscribe [::bp/screen]))]]
     ]))
