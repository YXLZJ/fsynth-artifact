(defpackage rb
  (:use cl)
  (:import-from sort compare)
  (:import-from util nor while)
  (:export clear
	   do-tree
	   find-node 
	   get-node
	   new-root node-count
	   remove-node root root-node
	   set-node
	   tests benchmarks))