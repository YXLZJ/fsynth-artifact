(defun suggest-size (layout element size)
  (with-vars (x y w h layout) element
    (declare (ignore x y))
    (cass:suggest w (alloy:to-un (alloy:size-w size)))
    (cass:suggest h (alloy:to-un (alloy:size-h size)))
    (cass:update-variables (solver layout))))