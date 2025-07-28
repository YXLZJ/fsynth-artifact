(defmethod gref ((g g) (bb symbol))
  "Reference GA object by basis blade keyword name"
  (aref (coef g) (position bb (basisbladekeys g))))