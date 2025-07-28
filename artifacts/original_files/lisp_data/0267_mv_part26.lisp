(defun makeg (class &rest args)
  "Create GA object of specified class. Provide basis-bitmaps & coefficients to populate.
E.g. (makeg ve2 #b1 1 #b10 2)"
  (w/g tmp class
    (loop while args
       for b = (pop args)
       for c = (pop args)
       when c do (gset tmp b c))))