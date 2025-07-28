(defun tb-cursor-iter (tb)
  "set iter0 to cursor"
  (with-slots (iter0) tb
    (%gtb-get-iter-at-mark tb iter0 (gtb-get-insert tb))
    iter0))