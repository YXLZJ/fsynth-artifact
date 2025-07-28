
(defun remove-handler (reg event-class handler)
  (with-slots (handler-table) reg
    (with-instance-lock-held (reg)
      (when-let ((handler-list (gethash event-class handler-table)))
        (deletef (cdr handler-list) handler)))))