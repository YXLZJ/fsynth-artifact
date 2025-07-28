(defun sum-list (lst)
  (if (null lst)
	0
	(+ (car lst) (sum-list (cdr lst)))))