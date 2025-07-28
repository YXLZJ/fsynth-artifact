(defmethod (setf lazy-nd-ref) (val (a lazy-nd-array) &rest indexes)
  (force-lazy-nd-ref a indexes)
  (with-slots (contents) a
    (set-nested-array contents (first indexes) (rest indexes) val)))