(defun q-list (start end &optional (scale sc-extensions:*scale*) keep-duplicate-p )
  (let* ((list (pc:quantize-list (alexandria:iota (- end start) :start start) scale)))
    (unless keep-duplicate-p
      (setf list (remove-duplicates list)))
    list))