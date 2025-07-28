(defop ldf (vm code)
    "Load a function to S register."
  (let ((f (list :fn :code code :env (vm-e vm))))
    (push f (vm-s vm))))