(defmethod ber-encode ((value set-request-pdu))
  (ber-encode-pdu value +set-request-pdu+))