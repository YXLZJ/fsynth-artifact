(defmethod print-object ((e invalid-cron-expr) out)
  (format out "#<CRON ~a>" (invalid-cron-expr-message e)))