(defun close-character-source-accidentally-left-open ()
  (when *open-stream-of-source-characters*
    (close-character-source-file)))