(defcoder linj-typer ((node code) cast-expression)
  (msg "Adding Java type cast")
  (make-lisp-the (java-to-lisp-type (type-qualified-name node)) code))