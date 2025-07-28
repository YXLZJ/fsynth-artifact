
(defun find-k (m s k)
  (if (or (>= k (* m 2)) (> k (/ s (* m 2))))
      -1
      (if (and (zerop (mod (/ s (* m 2)) k)) (= (gcd k m) 1))
          k
          (find-k m s (+ k 2)))))