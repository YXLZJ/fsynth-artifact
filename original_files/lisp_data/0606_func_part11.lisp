(defexport-fun "SDL_CompareAndSwapAtomicU32" :bool
  (a (:pointer (:struct atomic-u32)))
  (old-val :uint32)
  (new-val :uint32))