(define-command visit-buffer
    ((buffer-name (or (first *buffer-history*) 
		      (buffer-name (current-buffer)))))
  (switch-to-buffer buffer-name))