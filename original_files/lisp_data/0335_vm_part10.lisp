(defun vm-eval (&key (start-pc 0))
  (funcall (vm-compile :start-pc start-pc)))