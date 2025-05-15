(defun rv2. (a b)
  (declare (type rv2 a b))
  (+ (* (vx a) (vx b))
     (* (vy a) (vy b))))