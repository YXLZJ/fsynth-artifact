(defun get-s16 (bs offs)
  (let ((v (get-u16 bs offs)))
    (if (< v 32768) v (- v 65536))))