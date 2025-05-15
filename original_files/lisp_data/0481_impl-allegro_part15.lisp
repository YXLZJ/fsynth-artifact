(defun make-recursive-lock (&optional name)
  (mp:make-process-lock :name (or name "Anonymous recursive lock")))