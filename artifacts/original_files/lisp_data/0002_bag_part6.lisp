(defmethod sequences:elt ((bag bag) index)
  (svref (%objects bag) index))