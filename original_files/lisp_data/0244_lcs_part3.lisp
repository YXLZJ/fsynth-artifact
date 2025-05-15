(defun lcs-length (a b)
  "Compute the length of the longest common subsequence of vectors `a' and `b'"
  (multiple-value-bind (table m n) (%lcs-table a b)
    (aref table n m)))