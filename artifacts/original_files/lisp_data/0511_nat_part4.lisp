(define
  (mult
    (-> nat nat nat)
    (fun (x nat)
     (fun (y nat)
      (match x
        (z z)
        ((s x2) (plus y (mult x2 y))))))))