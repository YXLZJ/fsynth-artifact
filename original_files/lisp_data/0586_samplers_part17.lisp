(defmethod initialize-instance :after ((sampler dpn-sampler) &key array &allow-other-keys)
  (setf (dpn-sampler-neighbors-map sampler)
        (neighbors-map array)
        (dpn-sampler-neighbors-hist sampler)
        (neighbors-hist
         (dpn-sampler-neighbors-map sampler))))