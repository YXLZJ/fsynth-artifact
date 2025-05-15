(defun nice-keyword (string)
  (s-sql:from-sql-name (nice-name string)))