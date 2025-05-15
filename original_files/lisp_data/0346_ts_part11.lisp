(defun url2fn (url)
  (replace-substrings (rm-http url) "\/" "_")) 