(ns myNotesApp.components.notes
    (:require [myNotesApp.state :as state]
              [myNotesApp.helpers :refer [generate-key]]
              [reagent.core :as r]))


(defn save-or-edit-note
  [id]
  (let[
  tmpID (if (empty? id) (generate-key) id)
  tmpCaption (if (empty? (@state/tmpNote :caption)) "Title - Blank" (@state/tmpNote :caption))
  tmpText (if (empty? (@state/tmpNote :text)) "Text - Blank" (@state/tmpNote :text))]

  (swap! state/notes assoc tmpID {:id tmpID :caption tmpCaption :text tmpText})
  (reset! state/tmpNote {}))
)

(defn notes
  []
  (let [ delete-note #(swap! state/notes dissoc %) ]
    (fn
      []
      [:main
       [:div.notes
           [:div.btn.btn--primary.float--left.tooltip.far.fa-save
           {:data-tooltip "Save the new note"
              :on-click (fn [] (save-or-edit-note nil))}
           ]

        (for [{:keys [id caption text]} (vals @state/notes)]
          [:div.note {:key id}
           [:div.note__body
            [:div.note__title

              [:div.btn.btn--primary.float--right.tooltip.far.fa-trash-alt
                {:data-tooltip "Delete"
                :on-click #(delete-note id)}
              ]

              [:div.btn.btn--primary.float--right.tooltip.fa.fa-edit
                {:data-tooltip "Edit"
                :on-click (fn [] (save-or-edit-note id))}]
              caption]
            [:p.note__desc text]]])
        ]
      ]
    )
))
