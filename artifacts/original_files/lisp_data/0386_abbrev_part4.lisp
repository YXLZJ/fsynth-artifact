(defun scan-buffer-words (buffer)
  (with-open-stream (in (make-buffer-input-stream (buffer-start-point buffer)))
    (loop :for str := (read-line in nil)
          :while str
          :append (scan-line-words str))))