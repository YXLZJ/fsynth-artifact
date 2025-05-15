(defun rotate-z (x y angle)
  (matrix-combine *parent* (centered x y 0 (rotation-z angle))))