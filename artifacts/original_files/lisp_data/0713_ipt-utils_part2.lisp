(defun validate-name (name &optional err-code err-message)
  (if (cl-ppcre:scan "^[a-zA-Z0-9_]+$" name)
      name
      (ipt-abort-request-handler 400 (or err-code 400) (or err-message (format nil "Invalid name: ~a" name)))))