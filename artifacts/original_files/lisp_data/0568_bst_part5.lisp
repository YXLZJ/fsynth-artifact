(defun bst-max (bst)
  (and bst
       (or (bst-max (node-r bst)) bst)))