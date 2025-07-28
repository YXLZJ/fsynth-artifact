(defun create (stream &key chunk-size)
  (make-binary-buffer
   :stream stream
   :chunk-size (or chunk-size 65536)
   :chunks (make-array 10 :adjustable t :fill-pointer 0)))