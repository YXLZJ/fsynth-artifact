(defmethod lazy-nd-ref :around ((a lazy-nd-array) &rest indexes)
  (validate-indexes-n a indexes)
  (call-next-method))