(defun parse/err (string)
  (handler-case (parse string)
    (js-parse-error (e)
      (js-error :syntax-error (princ-to-string e)))))
