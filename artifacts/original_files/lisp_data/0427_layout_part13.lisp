(defmethod (setf alloy:bounds) :after (extent (layout layout))
  (alloy:refit layout))