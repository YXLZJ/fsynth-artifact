(defun fmt-mhz (mhz)
  (if (>= mhz 1000)
        (format nil "~,2FGHz" (/ mhz 1000))
        (format nil "~DMHz" mhz)))