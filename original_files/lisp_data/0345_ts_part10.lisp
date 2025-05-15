(defun rm-http (url)
  (subseq (second-lv (split_at1 url ":")) 2))