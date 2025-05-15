(defun check-array-bounds (a i j)
  (check-bounds i (rows a) "rows")
  (check-bounds j (columns a) "columns"))