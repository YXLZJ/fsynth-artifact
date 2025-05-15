(defun op-?-clause-condition (clause-operands)
  (declare (type list clause-operands))
  (destructuring-bind (channel-form &optional lambda-list (condition-form t))
      clause-operands
    (declare (ignore channel-form lambda-list))
    condition-form))