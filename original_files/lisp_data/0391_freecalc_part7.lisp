  (define-instance (Functor (ArithExprF :t))
    (define (map f ast)
      (match ast
        ((AddE t1 t2 cont) (AddE t1 t2 (map f cont)))
        ((SubE t1 t2 cont) (SubE t1 t2 (map f cont)))
        ((InputE cont) (InputE (map f cont))))))