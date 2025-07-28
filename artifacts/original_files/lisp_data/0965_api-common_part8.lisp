  (defn host-step (win) (values)
    "not external"
    (declare (speed 3) (safety 1) (debug 0)
             (profile t))
    (funcall step-func win)
    (values))