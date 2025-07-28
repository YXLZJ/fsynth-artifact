(defun call/save-directory (fn)
  (let ((start-dir (uiop:getcwd)))
    (unwind-protect
         (funcall fn)
      (chdir start-dir))))