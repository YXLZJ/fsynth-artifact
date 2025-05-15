(defclass hash/var (hash)
  ((hashfn  :reader  hash-fn
            :initarg :hashfn)))