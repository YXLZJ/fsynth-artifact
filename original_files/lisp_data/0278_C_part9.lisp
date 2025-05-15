(defun //stm (address value)
  (setf (aref *mem* address) value))