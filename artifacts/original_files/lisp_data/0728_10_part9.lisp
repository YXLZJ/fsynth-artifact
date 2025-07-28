(defun machines-to-list-of-values (machines)
  (nconc
   (loop for (m mm) on machines
	 when mm
	   append (n-times (get-x m) (- (get-cycle mm) (get-cycle m))))
   (list (get-x (first (last machines))))))