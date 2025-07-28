(defun hmac-verify-signature (digest-spec verification-key message signature
                              &key (start 0) (end (length message)))
  (equalp (hmac-sign-message digest-spec verification-key message
                             :start start :end end)
          signature))