      (setf (pend entry)
            (or aborted
                (not (equalp-with-case r (vals entry)))))