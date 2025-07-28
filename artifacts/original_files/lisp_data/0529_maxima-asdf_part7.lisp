(defun $asdf_load (name)
  (with-maxima-path-update name (asdf:load-system name)))