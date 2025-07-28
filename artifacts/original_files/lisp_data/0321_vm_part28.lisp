(defop eq (vm)
    "Check object equality between two values from S register."
  (push (eql (pop (vm-s vm)) (pop (vm-s vm))) (vm-s vm)))