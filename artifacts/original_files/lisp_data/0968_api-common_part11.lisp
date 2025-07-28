  (defun make-gl-context-current-on-surface (gl-context surface)
    (funcall make-current-func gl-context surface))