(defmethod ensure-instance :before ((glyph generic-transformed-glyph))
  (ensure-instance (generic-transformed-glyph-glyph glyph)))