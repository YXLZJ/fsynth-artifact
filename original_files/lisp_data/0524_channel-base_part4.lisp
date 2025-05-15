(defgeneric channel-open-p% (channel)
  (:method ((channel channel-base))
    (eq (channel-state channel) :open)))