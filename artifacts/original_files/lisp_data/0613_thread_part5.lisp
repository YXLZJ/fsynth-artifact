(defun lock-spinlock (spinlock)
  (loop while (sb-ext:compare-and-swap (car spinlock) nil t)))