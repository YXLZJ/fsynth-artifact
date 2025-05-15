(defmethod hash= ((hash1 hash) (hash2 hash))
  (vec= hash1 hash2))