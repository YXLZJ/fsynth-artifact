(defun maptree (fn x)
  (if (atom x)
      (funcall fn x)
      (let ((a (funcall fn (car x)))
	    (d (maptree fn (cdr x))))
	(if (and (eql a (car x)) (eql d (cdr x)))
	    x
	    (cons a d)))))