(defmethod shrink-frame :around ((frame standard-application-frame))
  (set-xwindow-state (xwindow-for-properties frame) +iconic-state+)
  (call-next-method))