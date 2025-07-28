(defmethod draw ((this sketch))
  (loop for m across (movers this) do (display m))
  (display (liquid this)))