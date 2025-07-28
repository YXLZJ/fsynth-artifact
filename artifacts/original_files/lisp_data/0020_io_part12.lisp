(defun read-image-file (file)
  (let ((fn (get-image-file-reader file)))
    (if fn
        (funcall fn file)
        (error "Cannot read image file: ~S" file))))