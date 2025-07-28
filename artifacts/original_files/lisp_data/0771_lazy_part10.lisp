(defun integers2 (n)
  (cons n (delay (integers2 (1+ n)))) )