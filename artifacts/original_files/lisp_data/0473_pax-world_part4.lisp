(defun list-designator (x)
  (if (or (listp x) (symbolp x) (functionp x))
      x
      (list x)))