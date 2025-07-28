(defmethod rollback ((modifier batch-modifier) image state)
  (declare (type list state))
  (loop with base-modifier = (batch-modifier modifier)
        for s in (reverse state) do
          (rollback base-modifier image s)))
