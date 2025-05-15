(defun next-type-id ()
  (with-slots (type-count) *vm*
    (let ((id type-count))
      (incf type-count)
      (assert (< type-count *max-type-count*))
      id)))
