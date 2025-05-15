(defun map-all-index-list-addresses (fn graph)
  "Call FN with the heap address of the elements of all index lists
used for indexing in GRAPH."
  (map-type-index-list-addresses fn graph)
  (map-edge-index-list-addresses fn graph))