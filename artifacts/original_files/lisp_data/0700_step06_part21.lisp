(defmethod backward ((func <exp>) gy)
  (let* ((x (@data (@input func)))
         (gx (aops:vectorize (x gy) (* (exp x) gy))))
    gx))