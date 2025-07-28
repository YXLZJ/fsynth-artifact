(defmethod ber-encode ((value snmpv2-trap-pdu))
  (ber-encode-pdu value +snmpv2-trap-pdu+))