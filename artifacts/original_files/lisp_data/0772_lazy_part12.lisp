(defun odd-numbers (n)
  (cons n (delay (odd-numbers (+ n 2)))) )