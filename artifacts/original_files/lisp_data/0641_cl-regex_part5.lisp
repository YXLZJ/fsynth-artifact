(defun re-to-lisp (str)
  (multiple-value-bind (parse-tree num-groups)
      (parse-regex str)
    (fa-to-lisp (convert parse-tree) num-groups)))