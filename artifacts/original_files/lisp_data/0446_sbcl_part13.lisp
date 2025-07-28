(defmethod process-status (process)
  (values (sb-ext:process-status process) (sb-ext:process-exit-code process)))