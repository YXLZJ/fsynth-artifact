(defun rv2rx (v)
  (declare (type rv2 v))
  (rv2 (- (vy v)) (vx v)))