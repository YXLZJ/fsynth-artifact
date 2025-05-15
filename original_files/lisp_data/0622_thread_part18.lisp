(defun unlock (lock)
  (sb-thread:release-mutex lock))
