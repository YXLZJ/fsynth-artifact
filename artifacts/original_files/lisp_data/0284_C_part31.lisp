(defun //array-element-type (addr)
  (//check-type addr +type/vector+)
  (//ldm (+ addr +lo/vector/elem-type+)))