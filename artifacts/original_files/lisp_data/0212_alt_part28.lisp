(defun op-clause-form (clause)
  (declare (type list clause))
  (destructuring-bind ((operator &rest operands) &body body) clause
    (ecase operator
      (! (op-!-clause-form operands body))
      (? (op-?-clause-form operands body)))))