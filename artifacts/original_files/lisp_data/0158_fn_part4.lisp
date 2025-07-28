(defmacro fn* (form &environment env)
  (fn*-internals form env))