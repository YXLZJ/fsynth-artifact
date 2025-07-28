
(defun tb-cursor-backwards(tb &key count )
  (with-slots (iter0) tb
    (tb-cursor-iter tb)
    (when (if count
	      (gti-backward-cursor-positions iter0 count)
	      (gti-backward-cursor-position iter0))
      (gtb-place-cursor tb iter0))))