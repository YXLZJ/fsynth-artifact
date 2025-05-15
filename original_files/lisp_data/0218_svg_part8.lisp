(defun tagname (obj)
  "Get the tag name of an object."
  (if (listp obj)
      (tagname (car obj))
      obj))