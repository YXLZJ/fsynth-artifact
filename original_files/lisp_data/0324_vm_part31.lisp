(defop cdr (vm)
    "Get cons cell's cdr from the top value of S register."
  (push (cdr (pop (vm-s vm))) (vm-s vm)))