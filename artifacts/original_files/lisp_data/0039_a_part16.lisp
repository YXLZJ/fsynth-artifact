(define filter
  (λ (pred list)
    (if (pred (first list))
      (cons (first list) (filter pred (rest list)))
      (filter pred (rest list)))))