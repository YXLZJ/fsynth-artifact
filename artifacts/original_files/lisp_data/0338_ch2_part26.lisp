(defun smaller ()
  (setf *big* (1- (guess-my-number)))
  (guess-my-number))