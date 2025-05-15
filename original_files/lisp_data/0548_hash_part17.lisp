(defmethod hash-length ((x hash))
  (length (hash-bytes x)))