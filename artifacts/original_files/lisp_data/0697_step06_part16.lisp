(defmethod forward ((func <square>) x)
  (aops:vectorize (x)
    (* x x)))