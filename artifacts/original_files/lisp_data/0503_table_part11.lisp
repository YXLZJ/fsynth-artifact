(defun set-row-column (column value row)
  "Sets the value of the given column inside the row."
  (setf (elt row column) value)
  row)