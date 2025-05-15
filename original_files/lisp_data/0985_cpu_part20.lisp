(defun cpu-modeline (ml)
  (declare (ignore ml))
  (format-expand *cpu-formatters-alist*
                 *cpu-modeline-fmt*))