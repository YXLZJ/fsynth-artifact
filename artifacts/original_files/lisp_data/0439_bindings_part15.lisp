(defun unix-to-universal-time (unix-time)
  (+ unix-time *unix-epoch-difference*))