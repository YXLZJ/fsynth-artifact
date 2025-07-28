(defmethod print-object ((a lazy-2d-array) stream)
  (with-slots (rows columns array) a
    (print-unreadable-object (a stream :type t)
      (format stream "(~Ax~A) ~A" rows columns array))))