(ns myNotesApp.components.form
    (:require [myNotesApp.state :as state]
              [myNotesApp.components.notes :refer [save-changes, save-new, clear-form-text-fields]]
              [myNotesApp.helpers :refer [generate-key]]
              [reagent.core :as r]))


(defn row [label input]
  [:div.row
   [:div.col-1 [:label label]]
   [:div.col-2 input]])


(defn input [label type id max-length value]
  (row label [:input.form-control
        {;:default-value "Input here..."
          :value @value
          :field type
          :id id
          :max-length max-length
          :on-change  #(reset! value (.. % -target -value))}]))


(defn form
  []
  (let []
     [:div.form
      [:div
       (input "Note - Caption :" :text :caption 10 state/tmp-caption)
       (input "Note - Text :" :text :text 20 state/tmp-text)]

        [:div.form__save-btn
          [:div.btn.btn--primary.float--left.tooltip.far.fa-save
          {:data-tooltip "Save as new"
            :on-click (fn [] (save-new))}
          ]
        ]

        [:div.form__edit-btn
          [:div.btn.btn--primary.float--left.tooltip.fa.fa-edit
          {:data-tooltip "Edit the note with changes"
          :on-click (fn [] (save-changes @state/tmp-id))}
          ]
        ]

        [:div.form__clear-btn
          [:div.btn.btn--primary.float--left.tooltip.fas.fa-eraser
          {:data-tooltip "Clear the form text fileds"
            :on-click (fn [] (clear-form-text-fields))}
          ]
        ]
      ]
    )
 )
