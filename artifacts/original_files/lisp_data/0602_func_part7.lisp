(defexport-fun "SDL_CompareAndSwapAtomicInt" :bool
  (a (:pointer (:struct atomic-int)))
  (old-val :int)
  (new-val :int))