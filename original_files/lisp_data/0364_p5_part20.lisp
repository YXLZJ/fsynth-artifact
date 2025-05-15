(defun p5-scale (&key s)
  (setf *current-transform* (scaling-matrix s))
  )