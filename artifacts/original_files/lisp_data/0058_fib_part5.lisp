(define (map f xs)
    (if (nil? xs)
        nil
        (let ((x (car xs)) (xs (cdr xs)))
            (cons (f x) (map f xs)))))