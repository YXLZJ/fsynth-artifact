(defmethod (setf channel-open-p%) (value channel)
  (setf (channel-state channel) (if value t nil)))
