(defun destroy-thread (thread)
  (signal-error-if-current-thread thread)
  (mp:process-kill thread))