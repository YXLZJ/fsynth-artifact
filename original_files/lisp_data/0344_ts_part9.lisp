(defun url2txt (url)
  (tsh (format nil "lynx -dump ~a" url)))