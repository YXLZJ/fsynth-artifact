(defun spat-get-dsp-commands (component-ptr)
  (let ((state-bundle (spat::OmSpatGetBundleFromGuiToDsp component-ptr)))
    (unwind-protect
        (odot::osc_decode_bundle_s_data state-bundle)
      (odot::osc_bundle_s_deepFree state-bundle))))