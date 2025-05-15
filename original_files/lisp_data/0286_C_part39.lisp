
(defun //length (addr)
  (cond
    ((eql (//type-of addr) +type/vector+)
     (//vint (//ldm (+ addr +lo/vector/length+))))
    (t
     (error "//length() wrong type, sorry"))))