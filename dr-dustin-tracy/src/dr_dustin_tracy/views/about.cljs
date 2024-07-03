(ns dr-dustin-tracy.views.about
  (:require
   [re-frame.core :as re-frame]
   [dr-dustin-tracy.events :as events]
   ))

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:on-click #(re-frame/dispatch [::events/navigate :home])}
     "go to Home Page"]]])