(defun get-s8 (bs offs)
  (let ((v (get-u8 bs offs)))
    (if (< v 128) v (- v 256))))