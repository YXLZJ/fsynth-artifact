(defun make-condition-variable (&key name)
  (declare (ignore name))
  (mp:make-gate nil))