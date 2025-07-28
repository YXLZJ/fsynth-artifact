(defun //alloc-mem (num-cells)
  "Allocate NUM-CELLs of memory, then return the address of the start
of it. The allocation is a simple increment of a pointer through
memory."
  (let ((mem *index*))
    (incf *index* num-cells)
    mem))