(defun close-character-source-file ()
  (close *open-stream-of-source-characters*)
  (setq *open-stream-of-source-characters* nil))