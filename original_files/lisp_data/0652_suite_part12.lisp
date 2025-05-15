(defun list-all-suites ()
  "Returns an unordered LIST of all suites."
  (hash-table-values *suite*))