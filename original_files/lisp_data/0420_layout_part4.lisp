(defmethod initialize-instance ((layout layout) &key)
  (call-next-method)
  (setf (gethash layout (variables layout))
        (make-variables layout (solver layout) :strength :strong)))