(defun write-image-file (file image)
  (let ((fn (get-image-file-writer file)))
    (if fn
        (funcall fn file image)
        (error "Cannot write image file: ~S" file))))
