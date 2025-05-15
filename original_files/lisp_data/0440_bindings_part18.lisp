(def-health-check verify-ccronexpr-is-loaded ()
  (cron-parse-expr "*/3 * * * * *"))
