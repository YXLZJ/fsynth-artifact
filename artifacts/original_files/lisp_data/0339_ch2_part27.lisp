(defun bigger ()
  (setf *small* (1+ (guess-my-number)))
  (guess-my-number))