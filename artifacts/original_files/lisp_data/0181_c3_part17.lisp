(define (assoc-old key records)
  (cond ((null? records) false)
	((equal? key (caar records)) (car records))
	(else (assoc-old key (cdr records)))))