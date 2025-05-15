(defun remove-0-len (x)
  (remove-if λ(= (length %) 0) x))