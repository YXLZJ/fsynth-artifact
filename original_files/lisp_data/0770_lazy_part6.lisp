(defmethod print-object ((d delayed-state) stream)
  (print-unreadable-object (d stream)
    (format stream "Delayed")))