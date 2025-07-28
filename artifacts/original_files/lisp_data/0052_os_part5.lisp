(defun chdir (&optional (dir (user-homedir-pathname)))
  "Set the operating system directory and sync
`*default-pathname-defaults*' to it."
  (let ((dir (cmd::resolve-dir dir)))
    (uiop:chdir dir)
    (setf *default-pathname-defaults* dir)))