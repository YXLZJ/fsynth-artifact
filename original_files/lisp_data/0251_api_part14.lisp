(defun %pathlist-to-cstring (pathname-list)
  (typecase pathname-list
    (null (null-pointer))
    (atom (%truename pathname-list))
    (cons (%pathname-concat pathname-list))))