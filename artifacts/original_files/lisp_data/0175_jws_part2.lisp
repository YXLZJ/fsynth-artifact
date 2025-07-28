(defun hmac-sign-message (digest-spec secret-key message &key (start 0) (end (length message)))
  (let ((hmac (ironclad:make-hmac secret-key digest-spec)))
    (ironclad:update-hmac hmac message :start start :end end)
    (ironclad:hmac-digest hmac)))