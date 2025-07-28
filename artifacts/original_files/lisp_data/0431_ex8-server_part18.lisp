            (set-io-handler *ex8-event-base*
                            (socket-os-fd client)
                            :read
                            (funcall io-buffer :read-some-bytes))