(defmethod fetch-word ((vm microvm) address &key advance)
  "Get a 16-bit value from the current position of memory, optionally
advancing the program counter by 2."
  (prog1 (+ (shl (elt (memory vm) address) 16 8)
            (elt (memory vm) (1+ address)))
    (when advance (incf (pc vm) 2))))