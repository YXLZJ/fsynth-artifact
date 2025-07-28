(defun p5-rotate (&key angle vx vy vz)
  (setf *current-transform* (rotation-matrix angle))
  )