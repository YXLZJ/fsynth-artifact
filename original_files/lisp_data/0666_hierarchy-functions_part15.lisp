(defun recompile-hierarchy (lfontology &optional krontology)
  (init-type-hierarchy)
  (ontology::add-ling-ontology-to-hierarchy lfontology *type-hierarchy*)
  (when krontology 
    (ontology::add-kr-ontology-to-hierarchy krontology *type-hierarchy*)
    )
  )