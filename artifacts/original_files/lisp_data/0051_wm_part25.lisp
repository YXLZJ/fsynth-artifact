(dotimes (i 10)
  (let ((wm-sel (alexandria:make-keyword (format nil "WM_S~d" i))))
    (defmethod release-selection (wm (selection (eql wm-sel)) object)
      (stop-wm wm))))