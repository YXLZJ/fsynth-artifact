(defun p5-point (&key x y z)
  (fly-to :x x :y y)
  (with-tool-down (*p5-depth*))
  )