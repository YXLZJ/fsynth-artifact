(defun rotation-x (angle)
  (let* ((c (cos angle))
         (s (sin angle))
         (n (- s)))
    (list 1 0 0
          0 c s
          0 n c
          0 0 0)))