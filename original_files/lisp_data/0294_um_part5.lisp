
(eval-when (:compile-toplevel :load-toplevel :execute)
  (defvar *operators* (make-hash-table)
    "Holds all the primitive operators defined via `defop'."))