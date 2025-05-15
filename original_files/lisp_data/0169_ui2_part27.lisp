(defun scale (x &optional y z)
  (matrix-combine *parent* (scaling x y z)))