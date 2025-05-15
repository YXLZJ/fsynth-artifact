(defmethod ber-encode ((value get-next-request-pdu))
  (ber-encode-pdu value +get-next-request-pdu+))