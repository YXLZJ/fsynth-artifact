  (defoptimizer (fast-mod derive-type) ((integer modulus))
    (declare (ignore integer))
    (derive-mod modulus))