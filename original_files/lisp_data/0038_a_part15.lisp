(define map
  (λ (f l)
    (if (empty? l)
      l
      (cons (f (first l))
            (map f (rest l))))))