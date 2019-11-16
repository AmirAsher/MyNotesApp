(ns myNotesApp.state
    (:require [reagent.core :as r]))


(def notes (r/atom {:note-01 {:id :note-01
                            :caption "a sample note caption"
                            :text "a sample note text (the sample note can't be edited)"}}))


(def tmp-id (r/atom ""))
(def tmp-caption (r/atom ""))
(def tmp-text (r/atom ""))
