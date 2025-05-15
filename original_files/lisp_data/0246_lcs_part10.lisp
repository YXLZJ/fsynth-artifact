(defun one-way-similarity (a b)
  "Like `similarity' but in only one direction."
  (float (/ (lcs-length a b) (length a)) 0d0))