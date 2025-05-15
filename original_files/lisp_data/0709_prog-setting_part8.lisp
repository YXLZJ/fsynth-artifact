(defcommand stumpwm-manual () ()
  "run stumpwm info manual"
  (run-shell-command
   (format nil "urxvt -e info ~a"
           (merge-pathnames "doc/stumpwm.info" *zz-load-directory*))))
