(defun render-class-precedence-list (precedence-list)
  "Render class PRECEDENCE-LIST."
  (make-paragraph
   (loop for head = precedence-list then (cdr head) while head
      collect (make-fixed-width (name* (car head)))
      when (cdr head) collect ", ")))