(defmethod forward ((func <exp>) x)
  (aops:vectorize (x)
    (exp x)))