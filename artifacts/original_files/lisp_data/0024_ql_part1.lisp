
(defun this-directory (&optional (path-extension ""))
  (merge-pathnames path-extension
                   (directory-namestring
                    (or *load-pathname* *default-pathname-defaults*))))