(defun get-image-stream-writer (type)
  (let* ((key (intern (string-upcase type) :keyword)))
    (gethash key *image-stream-writer-hash-table*)))