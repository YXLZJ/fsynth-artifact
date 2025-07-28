(defstruct (td (:print-function td-printer))
  (level 0)
  term
  pred
  mode)