(defun slice (vector a b &optional (type t) (dimension (- b a)))
  (make-array dimension :element-type type :displaced-to vector :displaced-index-offset a))