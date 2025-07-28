(defun spat-get-state (component-ptr)
  (let ((state-bundle (spat::OmSpatGetStateAsOscBundle component-ptr)))
    (unwind-protect
        (odot::osc_decode_bundle_s_data state-bundle)
      (odot::osc_bundle_s_deepFree state-bundle))))