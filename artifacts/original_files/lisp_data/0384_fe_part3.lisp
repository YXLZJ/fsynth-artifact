(defun open-file (path)
  (with-open-file (in path :direction :input :if-does-not-exist nil)
    (when in
      (loop for line = (read-line in nil)
         while line collect line))))