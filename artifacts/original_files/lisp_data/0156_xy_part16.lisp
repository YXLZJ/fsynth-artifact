(defun xy-from-polar (rho theta)
  "Build an xy vector from polar coordinates [rho] and [theta]"
  (xy (* rho (cos theta))
      (* rho (sin theta))))