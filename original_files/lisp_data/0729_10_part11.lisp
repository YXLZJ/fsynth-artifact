(defun part1 (&optional (input *input*))
  (get-signal-strengths
   (machines-to-list-of-values
    (get-list-of-machine-states input))))