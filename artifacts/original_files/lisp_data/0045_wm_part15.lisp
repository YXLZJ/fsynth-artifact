
(defmethod frame-pretty-name ((frame foreign-application))
  (let ((window (foreign-xwindow frame)))
    (or (ignore-errors (net-wm-name window))
        (ignore-errors (xlib:wm-name window))
        "NoWin")))