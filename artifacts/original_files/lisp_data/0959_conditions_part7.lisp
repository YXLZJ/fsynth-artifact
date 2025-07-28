(define-simple-condition resource-depended-on (trial-error)
  (resource dependents) "The resource~%  ~a~%cannot be unstaged as it is depended on by~{~%  ~a~}" resource dependents)