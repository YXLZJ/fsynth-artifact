(defmethod process-kill ((process sb-thread:thread))
  (sb-thread:terminate-thread process))