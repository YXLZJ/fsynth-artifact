(defclass base-pdu ()
  ((variable-bindings  :type sequence
                       :accessor variable-bindings-of
                       :initform nil
                       :initarg :variable-bindings))
  (:documentation "All PDU type have a variable bindings"))