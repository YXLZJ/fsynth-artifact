(defun fn~r (function &rest args)
  "Partially apply args to function"
  (declare (optimize (speed 3) (safety 1) (debug 1))
           (function function))
  (lambda (&rest rest-of-the-args)
    (multiple-value-call function
      (values-list rest-of-the-args)
      (values-list args))))