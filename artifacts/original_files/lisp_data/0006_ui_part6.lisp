(defun ui-set-text (browser id text)
  "Sets the text on a label."
  (gtk:gtk-label-set-text (get-widget browser id) text))