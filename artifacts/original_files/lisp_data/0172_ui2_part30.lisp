(defun rotate-y (x z angle)
  (matrix-combine *parent* (centered x 0 z (rotation-y angle))))