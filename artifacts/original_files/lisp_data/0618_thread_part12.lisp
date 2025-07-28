
(defstruct rw-lock
  (mutex (bordeaux-threads:make-lock))
  (read-count 0)
  (write-lock nil))