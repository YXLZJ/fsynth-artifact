(defun add-individual (ea individual)
  "Adds INDIVIDUAL to POPULATION of EA. Usually called when
  initializing the EA."
  (vector-push-extend individual (population ea)))