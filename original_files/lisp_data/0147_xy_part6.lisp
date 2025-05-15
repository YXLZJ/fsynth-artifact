(defun xy-cross (a b)
  "Cross-product of two xy vectors [a] and [b] as a scalar"
  (- (* a.x b.y)
     (* a.y b.x)))