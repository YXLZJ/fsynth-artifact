(define cons
  (λ (a b)
    ((λ (list) (array_unshift list a) list)
     (if (= null b) (array) b))))