(defexport-fun "SDL_CompareAndSwapAtomicPointer" :bool
  (a (:pointer :pointer))
  (old-val :pointer)
  (new-val :pointer))