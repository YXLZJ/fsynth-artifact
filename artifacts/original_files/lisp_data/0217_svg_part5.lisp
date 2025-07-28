    (let ((x (read-from-string (getf my-plist :x)))
          (y (read-from-string (getf my-plist :y))))
      (+ x y))