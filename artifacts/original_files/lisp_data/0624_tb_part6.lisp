
(defun tb-iters-to-offsets (tb start end)
  (with-slots (iter0 iter1) tb
    (%gtb-get-iter-at-offset tb iter0 start)
    (%gtb-get-iter-at-offset tb iter1 end)))