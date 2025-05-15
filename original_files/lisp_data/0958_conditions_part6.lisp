(define-simple-condition context-creation-error (trial-error)
  (message context) "Failed to create an OpenGL context~@[:~%~%  ~a~]" message)