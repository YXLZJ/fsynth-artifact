(defmethod newg ((g g))
  "Create a new GA object of the same type given"
  (make-instance (type-of g)))