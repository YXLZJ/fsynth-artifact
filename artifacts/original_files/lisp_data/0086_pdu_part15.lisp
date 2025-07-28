(defmethod ber-encode ((value get-request-pdu))
  (ber-encode-pdu value +get-request-pdu+))