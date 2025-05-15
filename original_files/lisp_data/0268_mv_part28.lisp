(defmethod print-object ((g g) stream)
  (format stream "#<~a~{ :~a ~a~}>"
	  (type-of g)
	  (loopg b c g
	     for bb in (basisblades g)
	     unless (numberzerop c)
	     collect bb
	     and collect c)))