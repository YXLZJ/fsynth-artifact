(defun unlock-spinlock (spinlock)
  (setf (car spinlock) nil))