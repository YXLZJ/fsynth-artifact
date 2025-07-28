(defun rv2x (a b)
  (declare (type rv2 a b))
  (- (* (vx a) (vy b))
     (* (vy a) (vx b))))