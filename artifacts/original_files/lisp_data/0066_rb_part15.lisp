(defun red? (node)
  (declare (type (or node null) node))
  (and node (node-red? node)))