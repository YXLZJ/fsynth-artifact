(defmethod setup-font (out (font ps-font) size name)
  (setf (ps-font-name font) name)
  (format out "/~A /~A findfont ~A scalefont def~%"
          name (font-name font) size))