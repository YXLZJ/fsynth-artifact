(defun v2. (a b)
  (declare (type v2 a b))
  (+ (* (vx a) (vx b))
     (* (vy a) (vy b))))