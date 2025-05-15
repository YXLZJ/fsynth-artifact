(defcoder linj-typer ((node code) array-creation)
  (setf (lisp-array-type code) (java-to-lisp-name (type-name node)))
  code)