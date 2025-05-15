(defvar *enabled-cors-resources* "resource://turtl-at-lyonbros-dot-com"
  "When set, will enable CORS for resource:// origins if they match the given
   string. Entries should be comma separated (this string is passed verbatim in
   the Access-Control-Allow-Origin header).")