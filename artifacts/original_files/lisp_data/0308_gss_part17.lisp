(defmethod glass:context-principal-name ((context ntlm-context) &key)
  (format nil "~A@~A" (ntlm-context-user context) (ntlm-context-domain context)))