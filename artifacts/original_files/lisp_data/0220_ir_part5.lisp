(defun load-ir (ir)
  (let ((loader (first ir))
        (arguments (rest ir)))
    (when loader
      (apply (get-ir-loader loader) arguments))))