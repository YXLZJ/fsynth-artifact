
(defclass multi-slot-generic-function-class (standard-generic-function)
  ((vslots :initform (make-hash-table :weakness :value)))
  (:metaclass c2mop:funcallable-standard-class))