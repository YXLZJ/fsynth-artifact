(defun rotr (node)
  (declare (type node node))
  (let ((l (node-left node)))
    (setf (node-left node) (node-right l)
	  (node-right l) node
	  (node-red? l) (node-red? node)
	  (node-red? node) t)
    l))