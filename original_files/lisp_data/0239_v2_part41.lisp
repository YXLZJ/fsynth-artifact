
(defun rotation (radians)
  (let* ((r (d radians))
         (c (cos r))
         (s (sin r)))
    (%m3 c (- s) 0d0
         s c 0d0
         0d0 0d0 1d0)))