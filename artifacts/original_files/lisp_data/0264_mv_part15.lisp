(defun numberzerop (n)
  "Return true if n is the number zero. NIL if non-zero or a non-number (e.g. symbol)."
  (and (numberp n) (zerop n)))