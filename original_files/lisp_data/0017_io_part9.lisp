(defun write-image-stream (stream type image)
  (let ((fn (get-image-stream-writer type)))
    (if fn
        (funcall fn stream image)
        (error "Cannot write image stream ~S of type ~S" stream type))))