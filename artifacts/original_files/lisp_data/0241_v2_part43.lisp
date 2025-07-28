(defun scale (sx &optional (sy sx))
  (%m3 (d sx) 0d0 0d0
       0d0 (d sy) 0d0
       0d0 0d0 1d0))