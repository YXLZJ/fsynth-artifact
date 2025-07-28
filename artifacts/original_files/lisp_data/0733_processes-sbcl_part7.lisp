(defmethod process-active-p ((me %process%))
  (eq (state me) :disable))