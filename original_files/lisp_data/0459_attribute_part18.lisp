(defmethod qualified-name ((attr attr))
  (with-slots (local-name namespace-prefix) attr
    (if namespace-prefix
        (format nil "~A:~A" namespace-prefix local-name)
        local-name)))