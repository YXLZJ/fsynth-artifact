(defmethod glut:idle ((w double-window))
  (with-slots (spin) w
    (incf spin 2.0)
    (when (> spin 360.0)
      (decf spin 360.0))
    (glut:post-redisplay)))