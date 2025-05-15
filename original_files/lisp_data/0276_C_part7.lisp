(defun //init-mem ()
  (setf *mem* (make-array *mem-length*)
        *index* 0))