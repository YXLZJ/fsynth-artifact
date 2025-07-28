(defun get-s32 (bs offs)
  (let ((v (get-u32 bs offs)))
    (if (< v 2147483648) v (- v 4294967296))))