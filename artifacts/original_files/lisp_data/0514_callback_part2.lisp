(defun register-callback (self callback-id fun)
  (let ((id (intern (string-upcase
                     (format nil "~a.~a" (id self) callback-id)))))
    (trc nil "registering callback" self :id id)
    (setf (gethash id (callbacks (nearest self gtk-app))) (cons fun self))
    id))