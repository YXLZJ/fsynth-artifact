(defun ivl-invert (lst &rest args)
  (let ((pivot (if (null args) (car lst)
                 (car args))))
    (cons (car lst) (mapcar (lambda (i) (- pivot (- i pivot))) (cdr lst)))))