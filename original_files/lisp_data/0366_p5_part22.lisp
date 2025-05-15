(defun p5-random (p1 &optional p2)
  (if p2
      (+ p1 (random (- p2 p1)))
      (random p1)))