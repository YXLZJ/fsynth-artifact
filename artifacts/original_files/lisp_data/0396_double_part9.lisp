(defmethod glut:mouse ((w double-window) button state x y)
  (declare (ignore x y))
  (case button
    (:left-button
     (when (eq state :down)
       (glut:enable-event w :idle)))
    ((:middle-button :right-button)
     (when (eq state :down)
       (glut:disable-event w :idle)))))