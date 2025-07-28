(defmethod (setf lazy-nd-ref) :around (val (a lazy-nd-array) &rest indexes)
  (validate-indexes-n a indexes)
  (call-next-method))