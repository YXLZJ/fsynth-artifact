
(test-client call
  (is (string= "world" (mrpc:call *client* "hello")))
  (is (= 3 (mrpc:call *client* "sum" 1 2)))  
  (is (null (mrpc:call *client* "nil"))))