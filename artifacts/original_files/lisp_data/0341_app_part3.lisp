(defun run-console-app-with-memory-db (&optional (books *book-list*))
  (let* ((book-repo (make-in-memory-book-repository books))
         (*browse-books-use-case* (make-browse-books-use-case book-repo)))
    (console-main-loop)))
