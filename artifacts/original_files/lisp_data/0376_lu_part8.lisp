    (when 
     (not (= j imax))
     (do ((k 0 (+ k 1)))
         ((> k (1- n)) t)
         (declare (type fixnum k))
       (setf dum (aref a imax k))
       (setf (aref a imax k) (aref a j k))
       (setf (aref a j k) dum))
     (setf (aref vv imax) (aref vv j)))