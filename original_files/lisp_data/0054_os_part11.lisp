(cffi:defcfun (%execv "execv") :int
  (path :string)
  (args (:pointer :string)))