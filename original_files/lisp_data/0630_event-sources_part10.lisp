(defmethod event-source-stop ((evsource event-source))
  (with-slots (thread alive) evsource
    (when (and thread (bt:thread-alive-p thread))
      (bt:destroy-thread thread)
      (setf thread nil
            alive nil))))