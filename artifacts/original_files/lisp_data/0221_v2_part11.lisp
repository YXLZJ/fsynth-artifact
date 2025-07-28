(defun ~ (a b epsilon)
  (or (= a b)
      (< (abs (- a b)) epsilon)))