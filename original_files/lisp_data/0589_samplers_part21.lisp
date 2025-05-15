(defmethod rollback ((flipper flipper) image state)
  (declare (optimize (speed 3)))
  (setf (image-pixel image state)
        (- 1 (image-pixel image state))))