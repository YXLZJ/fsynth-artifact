(defun make-lock (name &key type &allow-other-keys)
  "Returns a lock named name that is suitable for use with with-lock-held."
  (declare (ignore type))
  (sb-thread:make-mutex :name name)) 