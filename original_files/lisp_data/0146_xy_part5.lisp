(defun xy/ (a k)
  "Inverse scaling of an xy vector [a] by a scalar [k]"
  (xy (/ a.x k)
      (/ a.y k)))