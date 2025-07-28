(defun euler-50 ()
  (let* ((plength 1000) (slimit 1000000) (primes (gen-primes plength)))
	(check-prime-sum 0 plength primes plength slimit)))