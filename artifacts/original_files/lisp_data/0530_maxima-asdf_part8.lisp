(defun $asdf_compile (name)
  (with-maxima-path-update name (asdf:compile-system name)))