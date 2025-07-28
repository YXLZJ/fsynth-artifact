(defmethod print-object ((attr attr) stream)
  (print-unreadable-object (attr stream :type t :identity t)
    (with-slots (value) attr
      (format stream "~A=~S" (qualified-name attr) value))))