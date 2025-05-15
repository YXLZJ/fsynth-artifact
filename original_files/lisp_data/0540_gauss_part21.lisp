(defun gauss-solver (array vector)
  "Gauss algorithm with column pivot search."
  (multiple-value-bind (factored pivot-selection-vector)
      (gauss-factorization array)
    (gauss-backsubstitution
     factored (gauss-update factored pivot-selection-vector vector))))