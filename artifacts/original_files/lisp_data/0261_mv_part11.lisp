(defmethod gset ((g g) (bb symbol) val)
  "Set GA object of given basis blade keyword name to value"
  (setf (aref (coef g) (position bb (basisbladekeys g))) val))