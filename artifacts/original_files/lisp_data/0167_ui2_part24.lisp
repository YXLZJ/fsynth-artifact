(defun rotation-y (angle)
  (let* ((c (cos angle))
         (s (sin angle))
         (n (- s)))
    (list c 0 s
          0 1 0
          n 0 c
          0 0 0)))