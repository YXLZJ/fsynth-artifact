(defun p5-rect (&key x y width height)
  (p5-quad :x1 x :y1 y
	   :x2 (+ x width) :y2 y
	   :x3 (+ x width) :y3 (+ y height)
	   :x4 x :y4 (+ y height))
  )