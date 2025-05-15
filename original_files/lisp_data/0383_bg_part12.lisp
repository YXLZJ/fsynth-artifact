(defun escape-set-bg-8-bit (i)
  "Creates an ansi escape sequence for setting the background color using 8-bit
  lookup table
  "
  (escape-ansi 48 5 i))