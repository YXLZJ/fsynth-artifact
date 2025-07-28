(defun read-symbol-from-string (s)
  (with-input-from-string (s s)
    (read-symbol s)))