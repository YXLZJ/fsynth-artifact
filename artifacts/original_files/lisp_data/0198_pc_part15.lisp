(defun degree (value pc)
  (labels ((lp (i lst)
	     (if (null lst) (progn
			      (format t "pitch not in pc~%")
			      -1)
	       (if (= (car lst) (mod value 12)) i
		 (lp (+ i 1) (cdr lst))))))
    (lp 1 pc)))