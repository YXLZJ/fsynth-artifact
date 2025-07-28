(defun do-tests (&optional
                 (out *standard-output*))
  (dolist (entry (cdr *entries*))
    (setf (pend entry) t))
  (if (streamp out)
      (do-entries out)
      (with-open-file
          (stream out :direction :output)
        (do-entries stream))))