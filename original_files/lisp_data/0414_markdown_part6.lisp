
(defun text (stream value colon at &rest args)
  (declare (ignore args))
  (write-escaped-string
    (if colon
       value
       (write-to-string value :escape (not at) :case :downcase))
    stream))