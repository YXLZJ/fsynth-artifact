(defun suggest (layout element var size)
  (alloy:with-unit-parent layout
    (cass:suggest (element-var layout element var) (alloy:to-un size) :make-suggestable)
    (cass:update-variables (solver layout))
    (alloy:do-elements (element layout)
      (update layout element))))