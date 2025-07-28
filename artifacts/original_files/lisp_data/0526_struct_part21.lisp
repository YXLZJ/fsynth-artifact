(defun assert-text-token (thing)
  "Assert that THING is a valid text token. On failure signal a
TYPE-ERROR."
  (etypecase thing
    (list (check-type (first thing)
                      (member :bold :italic :fixed-width :url)))
    (string)))