(defun translate (x y &optional (z 0))
  (matrix-combine *parent* (translation x y z)))