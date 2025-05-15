(defgeneric errored (result)
  (:method (result) nil)
  (:method ((result reified-error)) t))