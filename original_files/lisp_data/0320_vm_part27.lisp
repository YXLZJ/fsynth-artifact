(defop atom (vm)
    "Check if is the top of S register atom."
  (let* ((v (pop (vm-s vm)))
         (res (cond ((numberp v) t)
                    ((symbolp v) t)
                    (t nil))))
    (push res (vm-s vm))))