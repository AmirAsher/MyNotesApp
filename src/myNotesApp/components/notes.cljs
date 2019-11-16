(ns myNotesApp.components.notes
    (:require [myNotesApp.state :as state]
              [myNotesApp.helpers :refer [generate-key]]
              [reagent.core :as r]))


(defn clear-form-text-fields
  []
    (reset! state/tmp-id "")
    (reset! state/tmp-caption "")
    (reset! state/tmp-text "")
)


(defn saving-helper
  [tmp-id tmp-caption tmp-text]
    (swap! state/notes assoc tmp-id {:id tmp-id :caption tmp-caption :text tmp-text})
    (clear-form-text-fields)
)


(defn save-new
  []
    (let[
      tmp-id (generate-key)
      tmp-caption @state/tmp-caption
      tmp-text @state/tmp-text
    ;  tmp-caption (if (identical? @state/tmp-caption "" ) "Title - Blank" (@state/tmp-caption))
    ;  tmp-text (if (identical? @state/tmp-text "" ) "Text - Blank" (@state/tmp-text))
    ]
    (saving-helper tmp-id tmp-caption tmp-text)
    )
)


(defn save-changes
  [id]
    (let[
      tmp-id (if (empty? id) (generate-key) id)
      tmp-caption @state/tmp-caption
      tmp-text @state/tmp-text
    ;  tmp-caption (if (identical? @state/tmp-caption "" ) "Title - Blank" (@state/tmp-caption))
    ;  tmp-text (if (identical? @state/tmp-text "" ) "Text - Blank" (@state/tmp-text))
    ]
    (saving-helper tmp-id tmp-caption tmp-text)
    )
)


(defn load-note
  [id caption text]
    (reset! state/tmp-id id)
    (reset! state/tmp-caption caption)
    (reset! state/tmp-text text)
)


(defn notes
  []
    (let [delete-note #(swap! state/notes dissoc %)]
      (fn
        []
        [:main
         [:div.notes
          (for [{:keys [id caption text]} (vals @state/notes)]
            [:div.note {:key id}
             [:div.note__body
              [:div.note__title

                [:div.btn.btn--primary.float--right.tooltip.far.fa-trash-alt
                  {:data-tooltip "Delete the note"
                  :on-click #(delete-note id)}
                ]

                [:div.btn.btn--primary.float--right.tooltip.fas.fa-file-upload
                  {:data-tooltip "Load the note"
                  :on-click (fn [] (load-note id caption text))}
                ]
                caption]
              [:p.note__desc text]]])
          ]
        ]
      )
    )
)
