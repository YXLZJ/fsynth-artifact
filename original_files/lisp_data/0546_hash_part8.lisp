(defmethod make-load-form ((ahash hash) &optional env)
  (make-load-form-saving-slots ahash :environment env))