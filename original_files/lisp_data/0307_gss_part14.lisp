(defun user-password-md4 (username)
  (declare (type string username))
  (let ((password (find-ntlm-user username)))
    (if password
        (password-md4 password)
        (error "User ~S not found" username))))