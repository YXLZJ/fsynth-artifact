(fli:define-foreign-function (%cron-next "cron_next")
  ((expr (:pointer fli-cron-expr))
   (date :time-t))
  :result-type :time-t)