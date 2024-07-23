{:nextjournal.clerk/visibility {:code :hide}}
(ns main "my-app" (:require 
                   [tablecloth.api :as tc]
                   [tablecloth.api.utils :as tcu]
                   [kixi.stats.distribution :refer [draw sample normal]]
                   [tech.v3.datatype.functional :as dfn]
                   [aerial.hanami.common :as hc]
                   [nextjournal.clerk :as clerk]
                   [aerial.hanami.templates :as ht]))

{:nextjournal.clerk/visibility {:code :hide :result :hide}}
(clerk/serve! {:browse true :watch-path "src/main.clj"})
{:nextjournal.clerk/visibility {:code :show :result :show}}

(def c-sd 15)
(def i-sd 15)

;; # How interview skills matter
;; IQ is standardized with a mean of 100 and a standard deviation of 15
(def sample-size 1000)
(def competencies (sample sample-size (normal {:mu 100, :sd c-sd})))
(def test-chart (hc/xform 
                 ht/bar-chart 
                 :DATA (tc/rows df :as-maps) 
                 :ENCODING {:x {:bin {:step 5}, :field "competency", :type "quantitative"}
                            :y {:aggregate "count", :type "quantitative"}}))
(def df (
         -> {:competency competencies}
         tc/dataset
         (tc/order-by :competency)))
(def df (tc/add-column df :crank (tcu/rank (:competency df))))
df
(clerk/vl test-chart)
;; We assume that people can fake competency in an interview. It's not uncommon for someone
;; with average competency can seem like they're capable of someone who's good, sometimes they are
;; able to look like someone great! At the same time, people can come off as under representive
;; either because they're shy, or have performance anxiety etc. For this part of the analysis
;; I'm going to assume that the competency sd is half that of the the original distribution
(def interview-skills (sample sample-size (normal {:mu 0 :sd i-sd})))
(def df (-> df
            (tc/add-column :interview-skills interview-skills)
            (tc/map-columns :apparent-skill '(:interview-skills :competency) (fn [fake real] (+ fake real)))
            (tc/order-by :apparent-skill)))
df
(def test-chart (hc/xform 
                 ht/bar-chart 
                 :DATA (tc/rows df :as-maps) 
                 :ENCODING {:x {:bin {:step 5}, :field "apparent-skill", :type "quantitative"}
                            :y {:aggregate "count", :type "quantitative"}}))
(clerk/vl test-chart)
    

;; test-chart
