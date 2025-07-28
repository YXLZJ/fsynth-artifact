(defun pc-random (lower upper pc)
  (if (null pc) -1
    (let ((choices (remove-if-not (lambda (x) (pc? x pc)) (loop for i from lower below upper
								collect i))))
      (if (null choices) -1
        (alexandria:random-elt choices)))))