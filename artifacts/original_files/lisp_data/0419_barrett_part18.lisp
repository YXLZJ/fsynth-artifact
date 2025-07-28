  (defun fast-mod (number divisor)
    "Is equivalent to CL:MOD for integer arguments"
    (declare (explicit-check))
    (mod number divisor))