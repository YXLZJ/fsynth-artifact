(defun generate-term-sentence-sample (filename terms size)
  (with-output-file (stream filename)
    (let ((windows (all-term-filtered-windows terms size)))
      (loop for window in windows do
	   (format stream "~A~%" (concat-words (tokens-for-ids (phrase-words (first window)))))))))