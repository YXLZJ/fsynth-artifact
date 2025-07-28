(defop leq (vm)
    "Check if `a` is less than or equals to `b`."
  (let ((b (pop (vm-s vm)))
        (a (pop (vm-s vm))))
    (push (<= a b) (vm-s vm))))
