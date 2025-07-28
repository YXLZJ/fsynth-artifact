(defmethod fetch-byte ((vm microvm) address &key advance)
  "Get an 8-bit value from the current position of memory, optionally
advancing the program counter by q."
  (prog1 (elt (memory vm) address)
    (when advance (incf (pc vm)))))