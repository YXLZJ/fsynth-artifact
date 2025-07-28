(defun get-image-stream-reader (type)
  (let* ((key (intern (string-upcase type) :keyword)))
    (gethash key *image-stream-reader-hash-table*)))