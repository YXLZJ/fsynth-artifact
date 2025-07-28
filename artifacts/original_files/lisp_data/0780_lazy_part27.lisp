(defmethod initialize-instance :after ((a lazy-nd-array) &rest initargs)
  (declare (ignore initargs))
  (with-slots (contents dimensions) a
    (setf contents (reify-dimension a dimensions))))