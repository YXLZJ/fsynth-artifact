
(defun fire-event (event emitter)
  (with-slots (handler-registry) emitter
    (invoke-handlers handler-registry event)))