(defun lambda-reader (stream char)
  (declare (ignore char))
  (let* ((body (read stream t nil t)))
    (fn*-internals body nil)))