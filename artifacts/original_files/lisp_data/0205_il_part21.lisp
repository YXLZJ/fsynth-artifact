(defun check-error ()
  (let ((error (get-error)))
    (unless (eq error :no-error)
      (cl:error (error-condition error)))))
