(defvar *production-error-handling* nil
  "If t, will attempt to catch all errors that make it to top-level and not let
   the parent process enter the debugger.")