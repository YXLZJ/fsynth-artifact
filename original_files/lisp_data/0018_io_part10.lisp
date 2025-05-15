(defun get-image-file-reader (file)
  (typecase file
    (string (get-image-file-reader (pathname file)))
    (pathname
     (let* ((type (pathname-type file))
            (key (intern (string-upcase type) :keyword)))
       (gethash key *image-file-reader-hash-table*)))))