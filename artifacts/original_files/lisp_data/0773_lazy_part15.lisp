(defmethod initialize-instance :after ((a lazy-2d-array) &rest initargs)
  (declare (ignore initargs))
  (with-slots (array rows columns) a
    (setf array (make-array rows))
    (dotimes (i rows)
      (setf (aref array i) (delay (make-array columns)))) ))