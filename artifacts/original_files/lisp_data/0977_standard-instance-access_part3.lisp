(defun (setf standard-instance-access) (new-value instance location)
  (cleavir-primop:nook-write instance location new-value)
  new-value)