(defmethod gref ((g g) (bb integer))
  "Reference GA object by basis blade bitmap"
  (aref (coef g) bb))