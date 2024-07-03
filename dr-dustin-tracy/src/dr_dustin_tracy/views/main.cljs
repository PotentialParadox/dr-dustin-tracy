(ns dr-dustin-tracy.views.main
  (:require
   [re-frame.core :as re-frame]
   [dr-dustin-tracy.routes :as routes]
   [dr-dustin-tracy.subs :as subs]
   ))

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    (routes/panels @active-panel)))