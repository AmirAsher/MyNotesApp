(ns myNotesApp.components.form
    (:require [myNotesApp.state :as state]
              [myNotesApp.helpers :refer [generate-key]]
              [reagent.core :as r]))

(defn form
  []
  (let []
     [:div.form
        [:div.form__title
          [:div.title "Add your note caption : "
            [:input
              {:type "text"
                  :on-change #(swap! state/tmpNote assoc :caption (.. % -target -value))}]
          ]
        ]

        [:div.form__text
          [:div.title "Add your note text : "
            [:input
              {:type "text"
                  :on-change #(swap! state/tmpNote assoc :text (.. % -target -value))}]
          ]
        ]
      ]
 ))
