(defun %make-thread (function name)
  (mp:process-run-function name function))