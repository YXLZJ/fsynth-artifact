      (if (and old-wm (not replace))
          (progn (xlib:destroy-window wm-sn-manager)
                 (error "Another WM is running~%"))
          (setf (xlib:selection-owner dpy *wm-selection* timestamp) wm-sn-manager))