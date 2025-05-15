(defop 13 REGLOAD
  (setf (:reg (ldb (byte 3 25) inst))
        (ldb (byte 25 0) inst)))