(define (last-pair x)
  (if (null? (mcdr x))
      x
      (last-pair (mcdr x))))