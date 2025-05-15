(test-client async-result
  (is (string= "You are async!" (mrpc:call *client* "async_result"))))