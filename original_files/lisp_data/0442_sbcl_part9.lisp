(defmethod signal-process (process (signal integer))
  (sb-ext:process-kill process signal))