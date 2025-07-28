  (defun window-size (win)
    "When given the host-specific window handle will return the size of the window"
    (funcall win-size-func win))