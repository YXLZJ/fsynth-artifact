(defmethod glop:on-event ((window plotwindow) (event glop:mouse-motion-event))
  (when (1st-pressed window)
    (incf (xangle window) (* *rotate-multiplicator* (glop:dx event)))
    (incf (yangle window) (* *rotate-multiplicator* (glop:dy event)))))