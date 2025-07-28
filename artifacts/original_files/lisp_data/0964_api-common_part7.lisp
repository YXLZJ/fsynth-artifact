  (defun set-make-gl-context-current-on-surface (func)
    "Call this and pass the function that will be called when the cepl needs to
     make the context current"
    (setf make-current-func func))