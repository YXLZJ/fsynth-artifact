
(defun run-testgame (initializer)
  "Run a test game interactively"
  (let* ((my-world     (funcall initializer))
	 (player       (adv:find-player "player" my-world)))
    (game-repl player)))