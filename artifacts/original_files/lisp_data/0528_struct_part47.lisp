(defun assert-element (thing)
  "Assert that THING is a valid element. On failure signal a TYPE-ERROR."
  (check-type thing list)
  (check-type (first thing) (member :paragraph :listing :table
                                    :plaintext :media :section)))