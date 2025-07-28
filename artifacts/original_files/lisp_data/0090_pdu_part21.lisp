(defmethod ber-encode ((value inform-request-pdu))
  (ber-encode-pdu value +inform-request-pdu+))