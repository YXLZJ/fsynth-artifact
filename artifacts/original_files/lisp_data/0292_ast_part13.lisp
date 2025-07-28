(defun subgoal-has-arbitrary-node-p (sub)
   (with-subgoal sub (:args args)
      (const-p (first args))))