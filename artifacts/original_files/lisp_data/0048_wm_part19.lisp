      (when old-wm
        (unless
            (dotimes (i 5)
              (handler-case (xlib:drawable-x old-wm)
                (xlib:drawable-error ()
                  (return t)))
              (sleep 1))
          (error "The old WM doesn't release the selection ownership.~%")))