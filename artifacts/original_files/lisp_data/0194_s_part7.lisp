(defun apply-tree (func tree)
  (multiple-value-bind (val changed) (funcall func tree)
    (if changed
        val
        (if (listp tree)
            (mapcar (lambda (x) (apply-tree func x)) tree)
            tree))))