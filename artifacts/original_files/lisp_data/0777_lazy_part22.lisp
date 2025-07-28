(defmethod lazy-ref ((a lazy-2d-array) (i integer))
  (with-slots (array) a
    (aref array i)))