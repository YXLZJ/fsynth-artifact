(defun chord-to-scale (root type)
  (scale (mod (+ (cadr (assoc type *chord->scale*)) root) 12)
         (cddr (assoc type *chord->scale*))))