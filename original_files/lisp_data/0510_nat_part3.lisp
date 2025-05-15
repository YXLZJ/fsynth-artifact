(define
  (plus
    (-> nat nat nat)
    (fun (x nat)
     (fun (y nat)
      (match x
        (z y)
        ((s x2) (s (plus x2 y))))))))