(defun m* (&rest matrices)
  (let ((r (if matrices
               (m3c (car matrices))
               (m3i))))
    (loop for m in (cdr matrices)
          do (%m* r m r))
    r))