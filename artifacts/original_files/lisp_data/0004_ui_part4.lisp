(defun ui-load-from-string (contents)
  "Loads the UI from a string template."
  (let ((builder (gtk:gtk-builder-new)))
    (gtk:gtk-builder-add-from-string builder contents)
    builder))