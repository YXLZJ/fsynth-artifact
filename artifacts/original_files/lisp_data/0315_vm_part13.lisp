(defop ldc (vm n)
    "Load a constant to S register."
  (push n (vm-s vm)))