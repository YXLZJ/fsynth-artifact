(defun render-type (name type-definition)
  "Render TYPE-DEFINITION with NAME."
  (destructuring-bind (&key kind documentation)
      type-definition
    (declare (ignore kind))
    (definition-template
      :kind "Type"
      :name name
      :document (docstring-document documentation))))