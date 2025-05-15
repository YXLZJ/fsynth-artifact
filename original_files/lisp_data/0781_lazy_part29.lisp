(defmethod rank ((a lazy-nd-array))
  (length (dimensions a)))