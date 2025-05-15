(defun make-sphere-stream (gpu-data-array)
  "returns an per instance sphere stream"
  (destructuring-bind (vert index)
      (nineveh.mesh.data.primitives:sphere-gpu-arrays
       :radius 1f0)
    (make-buffer-stream (list vert (cons gpu-data-array 1))
                        :index-array index)))