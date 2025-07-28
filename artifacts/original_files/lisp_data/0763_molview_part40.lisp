(defmethod glut:motion ((window mol-window) x y)
  (setf view-rotx (+ (car origrot) (- y (cadr origclick))))
  (setf view-roty (+ (cadr origrot) (- x (car origclick))))
  (glut:post-redisplay))