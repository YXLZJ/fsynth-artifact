(defstruct root
  (compare nil :type function)
  (key nil :type function)
  (node nil :type (or node null))
  (size 0 :type fixnum))