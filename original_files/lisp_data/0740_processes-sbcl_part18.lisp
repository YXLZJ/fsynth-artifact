(defmethod (setf process-priority) (val (process t))
  (declare (ignore val process))
  (values)
  )