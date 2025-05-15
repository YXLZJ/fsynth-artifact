(defmethod backward ((func <square>) gy)
  (let* ((x (@data (@input func)))
         (gy (aops:vectorize (x gy) (* x gy 2.0d0))))
    gy))