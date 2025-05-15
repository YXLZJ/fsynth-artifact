(defmethod process-kill ((me %process%))
  (setf (state me) :killed))