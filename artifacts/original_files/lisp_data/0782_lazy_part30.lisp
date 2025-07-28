(defmethod print-object ((a lazy-nd-array) stream)
  (with-slots (dimensions contents) a
    (print-unreadable-object (a stream :type t)
      (format stream "(~A) ~A" (join dimensions "x") contents))))