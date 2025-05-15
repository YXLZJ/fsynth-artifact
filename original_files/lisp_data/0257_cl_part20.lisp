(defun render-symbol-definitions (symbol definitions)
  "Render DEFINITIONS for SYMBOL."
  (let ((name (name* symbol)))
    (make-document
     (loop for definition in definitions collect
          (render-definition name definition)))))