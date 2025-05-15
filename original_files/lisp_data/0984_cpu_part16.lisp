(defun fmt-cpu-freq ()
  "Returns a string representing the current CPU frequency (especially useful for laptop users.)"
  (let ((mhz (parse-integer (get-proc-file-field "/proc/cpuinfo" "cpu MHz")
                            :junk-allowed t)))
    (fmt-mhz mhz)))