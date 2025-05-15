(defmethod apply-force ((mover mover) force)
  (let ((f (div force (mass mover))))
    (setf (acceleration mover) (add (acceleration mover) f))))