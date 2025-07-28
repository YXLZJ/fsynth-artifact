(defmethod random-direction ((dimensions (eql 2)))
  (declare (optimize (speed 3)))
  (let ((ϕ (random (* 2 +pi+))))
    (list (sin ϕ) (cos ϕ))))