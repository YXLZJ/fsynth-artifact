(defun flatten (x)
    (labels ((rec (x acc)
                  (cond ((null x) acc)
                        ((atom x) (cons x acc))
                        (t (rec
                             (car x)
                             (rec (cdr x) acc))))))
      (rec x nil)))