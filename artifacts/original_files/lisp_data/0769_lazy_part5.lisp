(defclass delayed-state ()
  ((next :initarg :next :reader next :documentation "generator function to produce the next value"))
  (:documentation "a class for delayed evaluation"))