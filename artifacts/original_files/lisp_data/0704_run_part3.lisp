(defun run-builder ()
  (build (asdf:system-relative-pathname :arrowgrams "build_process/lispparts/boot-boot.svg")))