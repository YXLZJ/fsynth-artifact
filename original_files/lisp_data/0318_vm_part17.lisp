(defop rtn (vm)
    "Exit from function evaluation and return a value on the top of S stack."
  (let ((v (pop (vm-s vm)))
        (dump (pop (vm-d vm))))
    (setf (vm-s vm) (append (list v) (getf dump :s))
          (vm-e vm) (getf dump :e)
          (vm-c vm) (getf dump :c))))