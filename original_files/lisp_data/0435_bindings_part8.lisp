(define-condition invalid-cron-expr (error)
  ((message :initarg :message
            :reader invalid-cron-expr-message)))