(defmethod select-font-instance ((instance ps-font-instance) out)
  (unless (ps-font-instance-name instance)
    (error "Selecting unnamed instance."))
  (format out "~A setfont~%" (ps-font-instance-name instance)))