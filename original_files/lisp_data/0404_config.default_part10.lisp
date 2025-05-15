(defvar *enable-hsts-header* nil
  "If NIL, Turtl won't pass back an HSTS security header. If this is set, it
   should be set to a integer value, which will be passed to the max-age value
   of the header.")