(defmethod cpg ((g g))
  "Copy GA object"
  (make-instance (type-of g) :coef (copy-seq (coef g))))