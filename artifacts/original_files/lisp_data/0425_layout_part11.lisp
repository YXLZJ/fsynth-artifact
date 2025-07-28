(defmethod alloy:leave :after ((element alloy:layout-element) (layout layout))
  (dolist (constraint (gethash element (constraints layout)))
    (cass:delete-constraint constraint))
  (remhash element (variables layout)))