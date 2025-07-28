(defun pc? (pitch pc)
  (consp (member (mod pitch 12) pc)))