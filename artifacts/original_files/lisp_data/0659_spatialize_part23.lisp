                        (unless (spat::OmSpatProcessAudio spat spat-out spat-in (n-samples input))
                          (error "ERROR IN SPAT DSP"))