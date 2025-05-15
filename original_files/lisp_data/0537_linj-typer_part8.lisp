(defcoder linj-typer ((node code) single-variable-declaration)
  (msg "Adding Java type declaration to single variable declaration")
  (make-lisp-name code "/" (java-to-lisp-type (type-qualified-name node))))