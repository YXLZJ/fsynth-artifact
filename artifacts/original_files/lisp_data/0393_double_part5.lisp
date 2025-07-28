(defmethod glut:display-window :before ((w double-window))
  (gl:clear-color 0 0 0 0)
  (gl:shade-model :flat))