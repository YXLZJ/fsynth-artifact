(defmethod rollback ((swapper swapper) image state)
  (destructuring-bind (coord1 coord2)
      state
    (let ((sample1 (image-pixel image coord1))
          (sample2 (image-pixel image coord2)))
      (setf (image-pixel image coord2) sample1
            (image-pixel image coord1) sample2))))