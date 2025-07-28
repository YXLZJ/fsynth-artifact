(defun map-all-graph-allocations (fn graph)
  "Call FN for each heap address referenced through a data structure
within GRAPH."
  (map-node-addresses fn graph)
  (map-all-index-list-addresses fn graph))