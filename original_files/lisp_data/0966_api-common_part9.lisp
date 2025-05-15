  (defn host-swap (win) (values)
    "not external"
    (declare (speed 3) (safety 1) (debug 0)
             (profile t))
    (funcall swap-func win)
    (values))