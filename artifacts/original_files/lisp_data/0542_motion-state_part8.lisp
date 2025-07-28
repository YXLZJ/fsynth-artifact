(defmethod (setf pose) (new-value (obj motion-state))
  (set-world-transform
   (foreign-obj obj)
   (cl-transforms:reference-transform new-value )))
