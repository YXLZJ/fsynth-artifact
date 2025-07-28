(defun gray (percentage &optional (alpha 1.0))
  (let ((amount (round (* percentage 255 1/100))))
    (rgb amount amount amount alpha)))