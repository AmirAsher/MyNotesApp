(ns myNotesApp.helpers)


(defn generate-key
  []
  (str "note-" (random-uuid)))
