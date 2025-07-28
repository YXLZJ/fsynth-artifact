(defun lock-recursive-spinlock (recursive-spinlock)
  (loop for ret = (sb-ext:compare-and-swap (car recursive-spinlock) nil sb-thread:*current-thread*)
        until (or (null ret) (eq ret sb-thread:*current-thread*))
        finally (incf (cdr recursive-spinlock))))