(cstruct flac--stream-metadata
         "FLAC__StreamMetadata"
         (type "type" :type flac--metadata-type)
         (is-last "is_last" :type flac--bool)
         (length "length" :type :unsigned-int)
         (data "data" :type (:union flac--stream-metadata-data)))
