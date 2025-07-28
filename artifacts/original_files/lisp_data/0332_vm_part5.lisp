(defmethod initialize-instance :after ((self vm) &rest args &key &allow-other-keys)
  (with-slots (main-scope proc-cache) self
    (push-proc (make-proc :stack (stack-copy nil)) :vm self)
    (setf main-scope (begin-scope :proc proc-cache))))