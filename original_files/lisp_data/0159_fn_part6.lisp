(defun normalized-arg-count (x)
  (let ((x (subseq (symbol-name (car (last x)))
                   1)))
    (if (equal x "")
        1
        (1+ (parse-integer x)))))