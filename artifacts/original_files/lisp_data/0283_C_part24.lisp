(defun //cdr (addr)
  (//check-type addr +type/cons+ "not a cons.")
  (//ldm (+ addr +lo/cons/cdr+)))