(defun print-lazy-td (td str)
  (princ "[" str)
  (print-td-rest td "]" str))