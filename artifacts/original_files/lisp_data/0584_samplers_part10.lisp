(defmethod random-direction ((dimensions (eql 3)))
  (declare (optimize (speed 3)))
  (let ((ϕ (random (* 2 +pi+)))
        (ψ (- (random +pi+) (/ +pi+ 2))))
    (list
     (sin ψ)
     (* (cos ψ) (sin ϕ))
     (* (cos ψ) (cos ϕ)))))