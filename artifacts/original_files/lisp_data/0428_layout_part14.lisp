(defmethod alloy:refit ((layout layout))
  (alloy:with-unit-parent layout
    (suggest-size layout layout (alloy:bounds layout))
    (alloy:do-elements (element layout)
      (update layout element))))