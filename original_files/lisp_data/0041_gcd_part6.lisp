(defun iter-gcd (u v)
  (until (zerop v)
    (shiftf u v (mod u v)))
  u)