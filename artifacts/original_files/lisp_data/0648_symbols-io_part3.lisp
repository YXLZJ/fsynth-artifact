(defun intern (name &optional (space *symbol-space*))
  (%intern name space))