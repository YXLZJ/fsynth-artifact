(defun null-or-quoted (x)
  (or (null x) (and (consp x) (eq (car x) *bq-quote*))))