(defconstant A (read))
(defconstant B (read))
(defconstant C (read))

(defun f (tt)
  (+ (* A tt) (* B (sin (* C tt pi)))))

(defun solve (h l i)
  (let ((m (/ (+ l h) 2)))
    (if (zerop i) (/ m 1.0d0)
        (if (< (f m) 100)
            (solve h m (1- i))
            (solve m l (1- i))))))

(defun main ()
  (format` t "~,11f~%" (solve 201 0 201)))
(main)
