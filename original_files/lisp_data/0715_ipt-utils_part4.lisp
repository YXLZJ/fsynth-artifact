(defun make-table-arg (table)
  (if (or (not table) (string= table "")) ""
      (format nil "-t ~a" (validate-name table 400 "Invalid table name"))))