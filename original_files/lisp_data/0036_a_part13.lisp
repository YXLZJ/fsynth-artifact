(define reduce
  (λ (fun init list)
    (if (empty? list)
      init
      (recur fun (fun init (first list-)) (rest list)))))