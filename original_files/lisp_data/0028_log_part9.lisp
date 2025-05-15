(defun ansi-code (severity)
  "Return an ANSI escape code used to print the severity on screen."
  (case severity
    ((:fatal :error) 31)
    ((:warning)      33)
    (t               32)))