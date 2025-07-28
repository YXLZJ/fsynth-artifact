
(defun rub-gensym (id)
  (intern (string (gensym id)) :rubbish)
)