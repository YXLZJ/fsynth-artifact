(defop ld (vm idx)
    "Load a value with `idx` in current envionment to S register."
  (let ((v (elt (vm-e vm) idx)))
    (push v (vm-s vm))))