(define fibs
    (let ((k 2)
          (double (lambda (x) (* k x)))
          (nums (map double (range -1 10))))
        (zip nums (map fib nums))))