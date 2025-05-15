(defun get-pixel-format-name (format-integer)
  "Returns the human readable name for a surface's pixel format, useful for debugging."
  (sdl2-ffi.functions:sdl-get-pixel-format-name format-integer))
