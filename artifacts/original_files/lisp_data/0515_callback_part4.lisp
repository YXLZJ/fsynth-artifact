(defun gtk-callback (self event fn)
  (format nil "gtk_server_connect(~A, ~A, :callback ~A)"
	  (id self) event (register-callback self event fn)))