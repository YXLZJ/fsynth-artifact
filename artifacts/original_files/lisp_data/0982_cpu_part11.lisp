(defun fmt-cpu-usage ()
  "Returns a string representing current the percent of average CPU
  utilization."
  (let ((cpu (truncate (* 100 (current-cpu-usage)))))
    (format nil *cpu-usage-modeline-fmt* (bar-zone-color cpu) cpu)))