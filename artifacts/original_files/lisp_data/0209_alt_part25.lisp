(defun op-clause-condition (clause)
  (declare (type list clause))
  (destructuring-bind ((operator &rest operands) &body body) clause
    (declare (ignore body))
    (ecase operator
      (! (op-!-clause-condition operands))
      (? (op-?-clause-condition operands)))))