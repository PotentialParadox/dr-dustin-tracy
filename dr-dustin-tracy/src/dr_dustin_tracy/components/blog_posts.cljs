(ns dr-dustin-tracy.components.blog-posts
  (:require
   [re-frame.core :as re-frame]
   [dr-dustin-tracy.events :as events]
   [cljs-time.format :as tf]))

(def date-to-string (tf/formatter "MMM-dd-yyyy"))

(defn post-title [{:keys [title url]}]
  [:a.text-2xl.hover:text-orange-500.text-orange-300.pb-2.pt-2.cursor-pointer
  {:on-click #(re-frame/dispatch [::events/navigate url])} title])

(defn list-item [{:keys [title date description figure url]}]
  (let [
        date (tf/unparse date-to-string date)]
    [:div.grid.grid-cols-12.bg-gray-400.rounded-lg.shadow-lg
     [:div.col-span-7.p-4
      [:div.text-white.text-sm (str "Posted: " date)]
      [post-title {:title title :url url}]
      [:div.hidden.md:block.text-white description]]
     [:div.col-span-5
      [:img.w-96.rounded-r-lg {:src figure}]]]))
