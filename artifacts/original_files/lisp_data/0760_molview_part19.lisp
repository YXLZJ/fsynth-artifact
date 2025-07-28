    (gl:translate (* light-dist (cos light-theta) (sin light-phi))
                  (* light-dist (sin light-theta) (sin light-phi))
                  (* light-dist (cos light-phi)))