(defun make-vm (debug-p code)
  (let ((code (ensure-secdm-package code)))
    (make-vm* :c code :debug-p debug-p)))