
(defun rv2n (v)
  (declare (type rv2 v))
  (let ((l (rv2mag v)))
    (assert (not (zerop l)))
    (rv2scale v (/ l))))