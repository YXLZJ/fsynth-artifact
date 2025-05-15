  (define a-program
    (do
     (x <- (input))
     (y <- (input))
     (res1 <- (add x y))
     (res2 <- (sub res1 3))
     (pure res2)))