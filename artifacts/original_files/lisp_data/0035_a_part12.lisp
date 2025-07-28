(define empty?
  (λ (list) 
    (if (= 0 (sizeof list))
      true
      false)))