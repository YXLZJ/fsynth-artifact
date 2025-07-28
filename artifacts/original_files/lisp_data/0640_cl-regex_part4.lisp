(defun re-graphviz (str)
  (node-to-graphviz (convert (parse-regex str))))