(defun url2txt-file (url)
  (let ((fn (str-cat (url2fn url) ".txt")))
    (str2file (url2txt url) fn)))
