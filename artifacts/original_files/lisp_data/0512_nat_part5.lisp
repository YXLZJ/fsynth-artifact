(define
  (leq
   (-> nat nat prop)
   (fun (x nat)
    (fun (y nat)
     (match x
      (z true)
      ((s x2)
        (match y (z false) ((s y2) (leq x2 y2)))))))))
