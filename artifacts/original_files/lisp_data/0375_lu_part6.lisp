      (do ((k 0 (+ k 1)))
          ((> k (1- j)) t)
          (declare (type fixnum k))
        (setf sum (- sum (* (aref a i k) (aref a k j)))))