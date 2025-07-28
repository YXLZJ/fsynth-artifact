(defun-javascript (dw lg) (msg)
  (send-ws-message (create :type "page-log" :message msg))
  (chain console (log msg)))