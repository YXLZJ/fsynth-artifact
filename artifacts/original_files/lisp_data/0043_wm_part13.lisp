(defmethod disable-frame :around ((frame standard-application-frame))
  (xlib:delete-property (xwindow-for-properties frame) :WM_STATE)
  (call-next-method))