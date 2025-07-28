(test-client unknown-method
  (signals mrpc:rpc-error (mrpc:call *client* "unknown" T))
  (handler-case (progn (mrpc:call *client* "unknown" T)
                       (fail))
    (mrpc:rpc-error (e) (is (string= "'unknown' method not found" (format NIL "~A" e))))))