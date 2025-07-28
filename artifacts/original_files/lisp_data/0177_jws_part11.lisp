(defun check-alg (headers algorithm)
  (equal (aget headers "alg")
         (if (eq algorithm :none)
             "none"
             (symbol-name algorithm))))