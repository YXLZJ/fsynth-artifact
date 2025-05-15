(defun acquire-lock (lock &optional (wait-p t))
  (mp:process-lock lock mp:*current-process* "Lock" (if wait-p nil 0)))