  (defun set-window-size-func (func)
    "Call this and pass the function that will be called when the cepl needs to
     query the window size"
    (setf win-size-func func))