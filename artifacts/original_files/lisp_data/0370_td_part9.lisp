(defun strip-last (l)
  (cond ((atom l) nil)
	((null (cdr l)) nil)
	(t (cons (car l) (strip-last (cdr l))))))