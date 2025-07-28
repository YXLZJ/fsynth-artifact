(defun ewmh-stop ()
  (let ((supporting-window (net-supporting-wm-check *wm-application*)))
    (setf (net-supporting-wm-check *wm-application*) nil)
    (xlib:destroy-window supporting-window)))