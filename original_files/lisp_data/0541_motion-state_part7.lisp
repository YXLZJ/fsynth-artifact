(defmethod pose ((obj motion-state))
  (cl-transforms:transform->pose (get-world-transform (foreign-obj obj))))