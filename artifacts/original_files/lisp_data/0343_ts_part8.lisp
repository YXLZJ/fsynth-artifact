(defun str2file (str fn)
   (with-open-file (strm fn :direction :output) 
     (write-string str strm)))