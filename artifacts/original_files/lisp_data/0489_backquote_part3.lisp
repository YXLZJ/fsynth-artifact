(defun bq-completely-process (x)
  (let ((raw-result (bq-process x)))
    (bq-remove-tokens (if *bq-simplify*
			  (bq-simplify raw-result)
			  raw-result))))