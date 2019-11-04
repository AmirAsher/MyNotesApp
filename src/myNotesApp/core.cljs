(ns myNotesApp.core
  (:require [reagent.core :as r]
            [myNotesApp.components.form :refer [form]]
            [myNotesApp.components.header :refer [header]]
            [myNotesApp.components.notes :refer [notes]]))


(defn app
  []
  [:div.container
  [header]
  [form]
  [notes]])


(defn ^:export main
  []
  (r/render
    [app]
    (.getElementById js/document "app")))
