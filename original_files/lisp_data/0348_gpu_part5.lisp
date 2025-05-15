(defmacro in (space &body body)
  (declare (ignore space body))
  (error "the 'in' macro can only be used inside shaders"))