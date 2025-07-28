(defun universal-to-unix-time (universal-time)
  (- universal-time *unix-epoch-difference*))