(defun read-image-stream (stream type)
  (let ((fn (get-image-stream-reader type)))
    (if fn
        (funcall fn stream)
        (error "Cannot read image stream ~S of type ~S" stream type))))