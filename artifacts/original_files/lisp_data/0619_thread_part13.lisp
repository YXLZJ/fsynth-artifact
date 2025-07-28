(defun read-lock (rw-lock)
  (loop
    (bordeaux-threads:with-lock-held ((rw-lock-mutex rw-lock))
      (unless (rw-lock-write-lock rw-lock)
        (incf (rw-lock-read-count rw-lock))
        (return-from read-lock)))
    (bordeaux-threads:thread-yield)))