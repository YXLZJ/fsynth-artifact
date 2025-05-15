(defun run-experiment (&key (exp-path "/Users/markus/experiments/invalid-semantics"))
  (run-test-sim M-init test-events :dest-path exp-path)
  t)
					     