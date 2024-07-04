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
     [:div.col-span-7.p-4
      [:div.text-white.text-sm (str "Posted: " date)]
      [:div.text-white.text-2xl.text-orange-300.pb-2.pt-2 title]
      [:div.hidden.md:block.text-white description]]
     [:div.col-span-5
      [:img.w-96.rounded-r-lg {:src figure}]]]))
