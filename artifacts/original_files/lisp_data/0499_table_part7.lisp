(defun add-to-table (row table)
  "Appends a row to the table."
  (vector-push-extend row table)
  table)