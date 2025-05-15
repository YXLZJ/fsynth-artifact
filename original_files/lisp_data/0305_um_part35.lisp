
(defun start (program-file)
  (declare (optimize
            (speed 3) (debug 0) (space 0)
            (safety 1) (compilation-speed 0)))
  (time (fetch-decode-execute (read-program program-file))))
