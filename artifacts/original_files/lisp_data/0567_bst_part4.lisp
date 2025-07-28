(defun bst-min (bst)
  (and bst
       (or (bst-min (node-l bst)) bst)))