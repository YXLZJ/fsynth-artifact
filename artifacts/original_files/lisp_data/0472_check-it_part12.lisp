(defun wrap-test-for-shrinking (test)
  "Return a function treating unhandled errors in TEST as failures."
  (lambda (arg)
    (handler-case
        (funcall test arg)
      (error () nil))))