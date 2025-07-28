(defun p5-translate (&key (tx 0) (ty 0) tz)
  (setf *current-transform* (translation-matrix tx ty))
  )