(define (make-accumulator x)
  (lambda (y)
    (begin (set! x (+ x y))
	   x)))