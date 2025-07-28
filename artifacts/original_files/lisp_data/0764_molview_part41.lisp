
(defun random-interval (bot top)
  (+ (* (- top bot) (/ (random 100000) 100000.0)) bot))