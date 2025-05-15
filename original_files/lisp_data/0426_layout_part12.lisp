(defmethod alloy:update ((element alloy:layout-element) (layout layout) &key constraints clear)
  (when clear
    (dolist (constraint (gethash element (constraints layout)))
      (cass:delete-constraint constraint)))
  (apply-constraints constraints element layout))