(test-client raise-error
  (signals mrpc:rpc-error (mrpc:call *client* "raise_error")))