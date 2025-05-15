(defmethod gset ((g g) (bb integer) val)
  "Set GA object of given basis blade bitmap to value"
  (setf (aref (coef g) bb) val))