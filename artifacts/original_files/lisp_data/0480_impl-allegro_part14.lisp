(defun make-lock (&optional name)
  (mp:make-process-lock :name (or name "Anonymous lock")))