                        (if (and (messages oscb)
                                 (null (spat-osc-command spat (messages oscb))))
                            (error "ERROR IN SPAT OSC-CONTROL PROCESSING"))