(defun op-!-clause-condition (clause-operands)
  (declare (type list clause-operands))
  (destructuring-bind (channel-form value-form &optional (condition-form t))
      clause-operands
    (declare (ignore channel-form value-form))
    condition-form))