(defmethod ber-encode ((value report-pdu))
  (ber-encode-pdu value +report-pdu+))