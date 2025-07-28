(defun get-node (root key)
  (declare (type root root))
  (let ((node (root-node root)))
    (while node
      (ecase (ccompare root key (node-key node))
	(:lt
	 (setf node (node-left node)))
	(:gt
	 (setf node (node-right node)))
	(:eq
	 (return))))
    (and node (node-value node))))