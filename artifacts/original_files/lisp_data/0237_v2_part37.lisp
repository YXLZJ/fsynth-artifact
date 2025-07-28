(defun rv2lerp (a b f)
  (rv2+ (rv2scale a (- 1.0 f))
        (rv2scale b f)))