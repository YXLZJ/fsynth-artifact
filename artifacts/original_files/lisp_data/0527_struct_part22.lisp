(defun assert-rich-text (thing)
  "Assert that THING is a valid rich text sequence. On failure signal a
TYPE-ERROR."
  (check-type thing list)
  (loop for token in thing do (assert-text-token token)))