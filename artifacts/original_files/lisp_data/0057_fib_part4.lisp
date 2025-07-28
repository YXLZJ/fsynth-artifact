(define (range m n)
    (if (> m n)
        nil
        (cons m (range (+ m 1) n))))