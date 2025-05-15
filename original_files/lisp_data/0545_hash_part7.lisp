(defclass hash (bev)
  ((val :reader hash-val
        :reader hash-bytes
        :initarg :vec)))