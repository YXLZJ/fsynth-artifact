(defmethod (setf lazy-2d-ref) (val (a lazy-2d-array) (i integer) (j integer))
  (check-array-bounds a i j)
  (setf (aref (lazy-ref a i) j) val))