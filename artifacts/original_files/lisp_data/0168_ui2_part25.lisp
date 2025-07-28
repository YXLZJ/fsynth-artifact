(defun rotation-z (angle)
  (let* ((c (cos angle))
         (s (sin angle))
         (n (- s)))
    (list c s 0
          n c 0
          0 0 1
          0 0 0)))