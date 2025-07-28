(defun find-symbol (name &optional (space *symbol-space*) exclude)
  (%find-symbol name space exclude))