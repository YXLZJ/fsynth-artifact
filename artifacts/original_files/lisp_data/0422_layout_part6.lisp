(defun element-var (layout element var)
  (with-vars (x y w h layout) element
    (ecase var
      (:x x)
      (:y y)
      (:w w)
      (:h h))))