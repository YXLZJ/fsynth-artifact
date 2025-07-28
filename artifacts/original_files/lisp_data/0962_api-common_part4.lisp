  (defun set-swap-func (func)
    "Call this and pass the function that will be called every time #'cepl:swap
     is called"
    (setf swap-func func))