(defun unlock-recursive-spinlock (recursive-spinlock)
  (when (zerop (decf (cdr recursive-spinlock)))
    (setf (car recursive-spinlock) nil)))