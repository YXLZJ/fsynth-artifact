(defun cut? (parser)
  "Parser modifier: discard all results but the first"
  (choice1 parser (zero)))