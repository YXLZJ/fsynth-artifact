(defmethod modify ((modifier batch-modifier) image)
  (loop with base-modifier = (batch-modifier modifier)
        repeat (batch-modifier-n modifier) collect
        (modify base-modifier image)))