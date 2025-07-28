
(defun invert (lst pc &rest args)
  (if (null args) (quantize-list (ivl-invert lst) pc)
    (quantize-list (ivl-invert lst (car args)) pc)))