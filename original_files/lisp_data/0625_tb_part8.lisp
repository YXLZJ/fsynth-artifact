(defun tb-remove-tag (tb tag start end)
  "Remove a tag at start/end offsets; return T"
  (tb-iters-to-offsets tb start end)
  (with-slots (iter0 iter1) tb
    (gtb-remove-tag tb tag iter0 iter1))
  t)