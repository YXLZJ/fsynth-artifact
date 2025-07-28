(defun register-package-regression-file (package regression-file)
  "Register a file to be used for saving all regression cases for a package."
  (setf (gethash
         (find-package package)
         *package-regression-files*)
        regression-file))