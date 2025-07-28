(defprint message
  (print-unreadable-object (obj stream :type t)
    (princ (sender obj) stream)
    (princ " → " stream)
    (princ (recipient obj) stream)
    (princ " " stream)
    (format stream "\"~A\"" (contents obj))))