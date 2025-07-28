                 ((eq (car (/type-of obj)) :place)
                  (format t "[")
                  (%unparse (/value obj))
                  (format t "]"))