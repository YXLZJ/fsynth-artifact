(defun dtransform (dx dy matrix)
  (values
   (+ (* dx (aref matrix 0)) (* dy (aref matrix 2)))
   (+ (* dx (aref matrix 1)) (* dy (aref matrix 3)))))