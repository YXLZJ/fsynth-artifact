      (if (< (+ last-update internal-time-units-per-second) (get-internal-real-time))
        (progn
          (format t "~a frames per second with ~a slices.~%" counter slices)
          (setq counter 0)
          (setq last-update (get-internal-real-time))))