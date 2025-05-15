(fli:define-c-struct fli-cron-expr
    (seconds (:c-array :uint8 8))
  (minutes (:c-array :uint8 8))
  (hours (:c-array :uint8 3))
  (days-of-week (:c-array :uint8 1))
  (days-of-month (:c-array :uint8 4))
  (months (:c-array :uint8 2)))