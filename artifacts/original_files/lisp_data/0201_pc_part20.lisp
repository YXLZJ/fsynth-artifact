(defun ivl-transpose (val lst)
  (mapcar (lambda (i) (+ i val)) lst))