(defun //svref (addr idx)
  (//check-type addr +type/vector+ "not a vector.")
  (//ldm (+ addr +lo/vector/data+ (//value idx))))