(define-simple-condition resource-not-allocated (trial-error)
  (resource) "The resource~%  ~s~%is required to be allocated, but was not yet." resource)