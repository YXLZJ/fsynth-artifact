(defun rotate-x (y z angle)
  (matrix-combine *parent* (centered 0 y z (rotation-x angle))))