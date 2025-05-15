(defun rectangular-table-p (table)
  "Returns true if all the rows in the table have the same number of elements."
  (or (= (num-rows table) 0)
      (let ((cols (num-cols (get-row 0 table))))
        (every (lambda (row)
                 (eql (num-cols row) cols))
               table))))