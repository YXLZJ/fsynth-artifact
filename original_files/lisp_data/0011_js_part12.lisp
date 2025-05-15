(define-condition js-condition (error)
  ((value :initarg :value :reader js-condition-value))
  (:report (lambda (e stream)
             (format stream "[js] ~a" (to-string (js-condition-value e))))))