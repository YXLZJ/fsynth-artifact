  (defoptimizer (%lomod derive-type) ((integer modulus))
    (declare (ignore integer))
    (derive-mod modulus))