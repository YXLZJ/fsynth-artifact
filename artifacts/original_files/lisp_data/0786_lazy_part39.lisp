(defun set-nested-array (a dimension dimensions val)
  (cond ((null dimensions) (setf (aref a dimension) val))
        (t (set-nested-array (aref a dimension) (first dimensions) (rest dimensions) val))))