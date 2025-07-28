(defun revbsign (b)
  "Sign of reversing a basis bitmap"
  (expt -1 (* 1/2 (logcount b) (- (logcount b) 1))))