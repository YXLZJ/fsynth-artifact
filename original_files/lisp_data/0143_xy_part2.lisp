(defun xy+ (&rest args)
  "Addition of xy vectors"
  (let ((xx 0)
        (yy 0))
    (dolist (p args)
      (incf xx p.x)
      (incf yy p.y))
    (xy xx yy)))