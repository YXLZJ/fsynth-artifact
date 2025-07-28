(defmethod generate-request-id ((pdu common-pdu))
  (with-slots (request-id-counter) pdu
    (portable-threads:atomic-incf request-id-counter)
    (the (unsigned-byte 32)
      (ldb (byte 32 0) request-id-counter))))