(defun add-to-row (value row)
  "Append a column to row and set it to the given value."
  (vector-push-extend value row)
  row)