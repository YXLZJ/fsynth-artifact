(defun validate-number (number &optional err-code err-message)
  (if (cl-ppcre:scan "^[0-9]+$" number)
      (parse-integer number)
      (ipt-abort-request-handler 400 (or err-code 400) (or err-message (format nil "Invalid number: ~a" number)))))