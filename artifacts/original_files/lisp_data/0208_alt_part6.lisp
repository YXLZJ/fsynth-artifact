(defun invoke-action (op)
  "Invokes the action associated with the given ALT-OPERATION."
  (declare (type alt-operation op))
  (ecase (direction op)
    (send (funcall (action op)))
    (receive (funcall (action op) (value op)))))