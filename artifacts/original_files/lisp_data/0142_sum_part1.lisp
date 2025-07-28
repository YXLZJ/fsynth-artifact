(define sum
	(lambda (s)
		(if (null? s)
			0
			(+ (car s) (sum (cdr s))))))