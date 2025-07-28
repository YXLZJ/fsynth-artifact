(defun scaling (x &optional y z)
  (when (undefined? y) (setf y x))
  (when (undefined? z) (setf z x))
  (list x 0 0
        0 y 0
        0 0 z
        0 0 0))