(defun check-bounds (i max dimension)
  (assert (<= 0 i (1- max)) (i) "lazy-2d-ref error. ~A value: ~D is out of range (0-~D)" dimension i (1- max)))