(defun escape-set-bg-rgb (r g b)
  "Creates an ansi escape sequence for setting the background color to rgb value
  "
  (escape-ansi 48 2 r g b))