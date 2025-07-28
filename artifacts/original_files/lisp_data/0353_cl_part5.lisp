(cl:defmacro letrec-syntax (cl:&whole whole binds cl:&body body)
  (cl:declare (cl:ignore binds body))
  (car (expand-top-level-forms! (list whole)
                                *cl-define-syntax-mstore*)))