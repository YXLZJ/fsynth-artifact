(defmethod process-active-p ((process sb-thread:thread))
  (sb-thread:thread-alive-p process))