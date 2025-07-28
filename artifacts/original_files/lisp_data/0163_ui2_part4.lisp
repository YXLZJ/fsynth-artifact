(defun dom-object (type)
  (let ((x (create-element type)))
    (setf (matrix x) (list 1 0 0
                           0 1 0
                           0 0 1
                           0 0 0))
    x))