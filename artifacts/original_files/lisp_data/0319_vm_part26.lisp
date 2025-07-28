(defop stop (vm)
    "Stop VM."
  (setf (vm-running-p vm) nil))