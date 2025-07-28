(defclass reified-error ()
  ((wrapped-error
    :initarg :wrapped-error
    :accessor wrapped-error)))