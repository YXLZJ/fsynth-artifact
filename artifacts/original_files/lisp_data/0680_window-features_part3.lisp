(defun window-contains-location-p (location window)
  (declare (optimize (speed 3) (space 0) (safety 1)))
  (and (>= location (interval-start window))
       (<= location (interval-end window))))