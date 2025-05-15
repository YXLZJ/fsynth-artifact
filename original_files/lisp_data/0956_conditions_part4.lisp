(define-simple-condition thread-did-not-exit (trial-error)
  (thread timeout) "Thread~%  ~a~%did not exit after ~ds." thread timeout)