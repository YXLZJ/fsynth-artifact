(defun update (layout element)
  (with-vars (x y w h layout) element
    (setf (alloy:bounds element)
          (alloy:px-extent (alloy:un (cass:value x)) (alloy:un (cass:value y))
                           (alloy:un (cass:value w)) (alloy:un (cass:value h))))))