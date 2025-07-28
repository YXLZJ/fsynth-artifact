(defop 12 LOAD
  (setf pc (:reg c))
  (let ((rb (:reg b)))
    (declare (type u32 rb))
    (unless (zerop rb)
      (let ((dup (copy-seq (:mem rb))))
        (setf (:mem 0) dup
              rom dup)))))