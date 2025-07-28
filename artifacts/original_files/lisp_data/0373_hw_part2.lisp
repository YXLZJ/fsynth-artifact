(defun hw (&optional (out *standard-output*))
  "Prints out a Hello, World! message."
  (format out "~&Hello, World!~%")
  (finish-output out)
  (values))