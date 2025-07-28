(defun rec-gcd (u v)
  (if (zerop v)
      u
      (rec-gcd v (mod u v))))