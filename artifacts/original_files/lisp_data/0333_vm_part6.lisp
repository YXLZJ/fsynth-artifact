(defun emit-op (op)
  (vector-push-extend op (code *vm*))
  op)