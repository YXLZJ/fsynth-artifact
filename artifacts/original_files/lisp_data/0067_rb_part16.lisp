(defun rotl (node)
  (declare (type node node))
  (let ((r (node-right node)))
    (setf (node-right node) (node-left r)
	  (node-left r) node
	  (node-red? r) (node-red? node)
	  (node-red? node) t)
    r))