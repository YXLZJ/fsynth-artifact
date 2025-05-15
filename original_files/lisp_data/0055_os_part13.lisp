(defun execvp (executable arglist)
  (let ((executable
          (namestring
           (resolve-executable executable))))
    (execv executable arglist)))