(defun lock (lock)
  (sb-thread:grab-mutex lock :waitp t))