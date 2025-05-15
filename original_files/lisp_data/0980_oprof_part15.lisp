(defun fib2 (n)
  (declare (xargs :guard (natp n)))
  (if (zp n)
      1
    (if (= n 1)
        1
      (+ (fib2 (- n 1)) (fib2 (- n 2))))))