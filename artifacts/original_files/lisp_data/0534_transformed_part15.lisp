(defmethod ensure-instance :before ((glyph transformed-glyph))
  (ensure-instance (transformed-glyph-glyph glyph)))