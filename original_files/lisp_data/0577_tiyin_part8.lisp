(with-open-file (stream (my file))
  (loop while (setf if (read stream nil)) do  (print it)))