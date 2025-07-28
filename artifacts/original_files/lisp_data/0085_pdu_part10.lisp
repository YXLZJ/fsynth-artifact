(defmethod print-object ((instance pdu) stream)
  (with-slots (request-id error-status error-index variable-bindings) instance
    (print-unreadable-object (instance stream :type t)
      (format stream "(~D (~D ~D)) ~A"
              request-id error-status error-index variable-bindings))))