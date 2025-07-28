(defun constant-cons-p (expr)
  (if (nil-p expr)
   t
   (let ((head (cons-head expr))
         (tail (cons-tail expr)))
    (and (literal-p head)
         (constant-cons-p tail)))))
