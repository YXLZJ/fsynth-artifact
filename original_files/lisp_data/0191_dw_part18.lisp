(defun clear-display-window ()
  (ps-eval-in-client (chain dw (clear-screen))))