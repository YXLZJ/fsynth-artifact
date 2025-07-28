(defun match (regex str)
  (funcall (eval (re-compile regex)) str))