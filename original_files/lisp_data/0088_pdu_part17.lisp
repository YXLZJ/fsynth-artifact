(defmethod ber-encode ((value response-pdu))
  (ber-encode-pdu value +response-pdu+))