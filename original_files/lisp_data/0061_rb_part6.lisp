(defstruct node
  (key nil :type t)
  (value nil :type t)
  (left nil :type (or node null))
  (right nil :type (or node null))
  (red? t :type boolean))