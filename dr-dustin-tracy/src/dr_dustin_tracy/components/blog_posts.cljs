(ns dr-dustin-tracy.components.blog-posts
  (:require
   [cljs-time.format :as tf]))


(def date-to-string (tf/formatter "MMM-dd-yyyy"))

(defn list-item [info]
  (let [title (get info :title)
        description (get info :description)
        date (tf/unparse date-to-string (get info :date))
        figure (get info :figure)]
    [:div.grid.grid-cols-12.bg-gray-400.rounded-lg.shadow-lg
     [:div.col-span-7
      [:div.text-white (str "Posted: " date)]
      [:div.text-white title]
      [:div.text-white description]]
     [:div.col-span-5
      [:img.w-96.rounded-lg {:src "images/voteman.png"}]]]))
