(defclass flipper (modifier)
  ((sampler :reader   modifier-sampler
            :initarg  :sampler
            :initform (error "Specify sampler")
            :type     sampler))
  (:documentation "A modifier which flips the phase of taken sample
(works with two-phase images only)."))