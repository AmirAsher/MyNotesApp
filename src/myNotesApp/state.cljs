(ns myNotesApp.state
    (:require [reagent.core :as r]))


(def notes (r/atom {:note-01 {:id :note-01
                            :caption "a sample note caption"
                            :text "a sample note text"}}))

(def tmpNote (r/atom {:nil {:id :nil
                            :caption ""
                            :text "" }}))
