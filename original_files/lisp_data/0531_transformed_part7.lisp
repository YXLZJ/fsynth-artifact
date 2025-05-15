(defun transform (dx dy matrix)
  (values
   (+ (* dx (aref matrix 0)) (* dy (aref matrix 2)) (aref matrix 4))
   (+ (* dx (aref matrix 1)) (* dy (aref matrix 3)) (aref matrix 5))))