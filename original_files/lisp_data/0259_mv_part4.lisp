(defun genrevtable (dim)
  "Generate reverse sign lookup table"
  (let ((size (expt 2 dim)))
    (make-array 
     size 
     :initial-contents
     (loop for b below size
	collect (revbsign b)))))