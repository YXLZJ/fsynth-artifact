(defmethod generate :around ((feed feed))
  (with-xml-output (make-string-sink) (call-next-method)))