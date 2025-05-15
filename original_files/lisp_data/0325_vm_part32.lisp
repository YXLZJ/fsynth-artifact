(defop add (vm)
    "Add two values."
  (let ((b (pop (vm-s vm)))
        (a (pop (vm-s vm))))
    (push (+ a b) (vm-s vm))))