(defun generate-to (file object)
  (with-open-file (stream file :direction :output
                          :if-exists :rename-and-delete)
    (write-string (generate object) stream))
  (probe-file file))
