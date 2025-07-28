(defun //car (addr)
  (//check-type addr +type/cons+ "not a cons.")
  (//ldm (+ addr +lo/cons/car+)))