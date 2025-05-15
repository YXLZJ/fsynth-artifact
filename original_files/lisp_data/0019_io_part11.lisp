(defun get-image-file-writer (file)
  (typecase file
    (string (get-image-file-writer (pathname file)))
    (pathname
     (let* ((type (pathname-type file))
            (key (intern (string-upcase type) :keyword)))
       (gethash key *image-file-writer-hash-table*)))))