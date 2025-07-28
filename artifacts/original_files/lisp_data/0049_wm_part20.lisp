      (unless (and (xlib:selection-owner dpy *wm-selection*)
                   (xlib:window-equal wm-sn-manager (xlib:selection-owner dpy *wm-selection*)))
        (xlib:destroy-window wm-sn-manager)
        (error "Failed to acquire WM selection ownership~%"))