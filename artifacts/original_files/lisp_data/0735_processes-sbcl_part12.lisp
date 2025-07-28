(defmethod process-preset ((me %process%) initial-function &rest initial-args)
  (setf (function-to-run me) initial-function)
  (setf (args-to-use me) initial-args)
  me)