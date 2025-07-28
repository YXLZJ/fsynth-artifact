(defun is-opt (string)
  (and (> (length string) 2) (string= (subseq string 0 2) "--")))