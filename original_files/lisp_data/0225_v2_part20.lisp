(defun v2scale (v f)
  (declare (type v2 v))
  (v2 (* f (vx v)) (* f (vy v))))