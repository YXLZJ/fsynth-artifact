(defun swap-rows (array i0 jth)
  "Interchange the "
  (loop
   for column below (array-dimension array 1) do
   (rotatef (aref array i0 column) (aref array jth column))
   finally (return array)))