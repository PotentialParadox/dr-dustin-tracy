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

(defn blog-post-header [{:keys [title date description]}]
  [:div.bg-gray-200.shadow-lg.h-fit.p-4.rounded-t-lg.pb-6
   [:div.text-black-300.text-2xl.lg:text-4xl.pb-2 title]
   [:div.text-black.text-left.pb-4 description]
   [:div.text-black.text-sm.text-left (str "Posted: " (tf/unparse date-to-string date))]])

(defn blog-hr [] [:hr {:class-name "h-0.5 bg-black mt-2 mb-2"}])

(defn blog-dislaimer [] [:div.bg-gray-200.p-4.text-sm.italic "The views expressed in this post are my own and do not reflect the views of any organization I am affiliated with."])

(defn blog-key-takeaways [{:keys [takeaways]}]
  [:div.bg-gray-200.shadow-lg.h-fit.p-4
  [:div.text-black-300.text-lg.lg:text-2xl.pb-4 "Key Takeaways"]
  [:ul {:class-name "list-disc pl-4 space-y-2"}
   (for [takeaway takeaways]
     [:li.text-black.border-b.border-slate-400.pb-2  takeaway])]])


