(defmethod hash-check (item (expected string))
  (string-equal expected (hex-str (hash/256 item))))