(defmethod lazy-2d-array-fill ((a lazy-2d-array) val)
 (setf (fill-flag a) t
       (fill-value a) val)
 (with-slots (array) a
   (loop for v across array
         unless (delayed-p v)
         do (fill v val))))