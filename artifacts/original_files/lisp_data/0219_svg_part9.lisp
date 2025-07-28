(defun get-node-attr (node attr-name)
  "Given a node, get the attribute stored under attr-name."
  (let ((val nil))
    (dolist (attr (cadr node))
      (when (equal (car attr) attr-name)
        (setf val (cadr attr))
        (return)))
    val))