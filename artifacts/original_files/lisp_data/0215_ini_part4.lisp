(defun read-from-string-maybe (string)
  (handler-case
      (let ((token (read-from-string string)))
        (if (numberp token) token string))
    (error () string)))