(defmethod ord:compare ((hash1 hash) (hash2 hash))
  (ord:compare (vec hash1) (vec hash2)))