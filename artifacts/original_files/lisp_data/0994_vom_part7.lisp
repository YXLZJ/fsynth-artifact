(defparameter *time-formatter*
  (lambda ()
    (multiple-value-bind (second minute hour)
        (get-decoded-time)
      (format nil "~2,'0D:~2,'0D:~2,'0D" hour minute second)))
  "A function of 0 args that returns the current time in the desired format.")