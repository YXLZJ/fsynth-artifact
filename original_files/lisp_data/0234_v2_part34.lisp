(defun rv2scale (v f)
  (declare (type rv2 v))
  (rv2 (* f (vx v)) (* f (vy v))))