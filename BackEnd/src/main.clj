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


;; # How interview skills matter
;; This first part of this series will be focused on brining up concepts, we'll
;; be fine tuning the model latter.
;; To begin with, we will model an individual skill similarly to how 
;; we model an individual's IQ, a normal distribution around 100 with a standard deviation of 15.
;; Anyone who has given interviews knows however that the distribution is far from normal,
;; most candidates are not very good at all and only a few are. We'll work with a different distribution later.
{:nextjournal.clerk/visibility {:code :show :result :hide}}
(def sample-size 100000)
(def skill-sd 15)
(def skill (sample sample-size (normal {:mu 100, :sd skill-sd})))
(def df (-> {:skill skill}
         tc/dataset
         (tc/order-by :skill)))
(def df (tc/add-column df :skill-rank (tcu/rank (:skill df))))
(def test-chart (hc/xform 
                 ht/bar-chart 
                 :DATA (tc/rows df :as-maps) 
                 :ENCODING {:x {:bin {:step 5}, :field "skill", :type "quantitative"}
                            :y {:aggregate "count", :type "quantitative"}}))

{:nextjournal.clerk/visibility {:code :show :result :show}}
(clerk/vl test-chart)
;; We assume that people can fake skill in an interview. It's not uncommon for someone
;; with average skill can seem like they're capable of someone who's good, sometimes they are
;; able to look like someone great! At the same time, people can come off as under representive
;; either because they're shy, or have performance anxiety etc. For this part of the analysis
;; I'm going to assume that the skill sd is roughly that of the the original distribution.
{:nextjournal.clerk/visibility {:code :show :result :hide}}
(def interview-skills (sample sample-size (normal {:mu 0 :sd skill-sd})))
(def df (-> df
            (tc/add-column :interview-skills interview-skills)
            (tc/map-columns :apparent-skill '(:interview-skills :skill) (fn [fake real] (+ fake real)))
            (tc/order-by :apparent-skill :desc)))

(def test-chart1 (hc/xform 
                 ht/bar-chart 
                 :DATA (tc/rows df :as-maps) 
                 :ENCODING {:x {:bin {:step 5}, :field :apparent-skill, :type "quantitative"}
                            :y {:aggregate "count", :type "quantitative"}}))
{:nextjournal.clerk/visibility {:code :show :result :show}}
(clerk/vl test-chart1)
;; The combined distribution now has a higher standard deviation. If we look at the
;; top performers, both by apparent and actual skill, you'll notice that the individuals
;; with the highest apparent skills have apparent skills that are far higher than any
;; real skill in the population. Those portraid as super genious, while smart are rarely nearly
;; as smart as they appear.
(clerk/table (-> df (tc/order-by :skill-rank :desc) tc/head))
(clerk/table (tc/head df))    
;; Lets look at the top 5% of the population based on apparent-skill
(def df-top (tc/head df 5000))
(def top-iskills (hc/xform 
                 ht/bar-chart 
                 :DATA (tc/rows df-top :as-maps) 
                 :ENCODING {:x {:bin {:step 5}, :field :interview-skills, :type "quantitative"}
                            :y {:aggregate "count", :type "quantitative"}}))
(clerk/vl top-iskills)
