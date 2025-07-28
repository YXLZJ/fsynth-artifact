(defun random-element (list)
  "Return random element from the list."
  (nth (random (length list)) list))