(defun define-module (module-name path)
  (setf (gethash module-name *defined-modules*) path))