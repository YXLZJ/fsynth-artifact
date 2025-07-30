#Appendix

## EpsilonRepair on program inputs defined by ANTLR format
```
-------------------------------------------------------------------------------------
Format   Alg        Avg BR     σ BR   Avg OR     σ OR    Avg t      σ t   Succ    Tot
-------------------------------------------------------------------------------------
single_dot Antlr     1147.00     0.00  1147.00     0.00     1.98     1.03      1   1000
single_dot DDMax        5.01    16.89     4.74    16.90    14.88    30.96    966   1000
single_dot DDMaxG     710.24   573.21   710.03   573.21    36.45    30.21    931   1000
single_dot erepair      5.28    18.34     4.87    18.39     1.95     5.95    952   1000
single_obj Antlr        0.00     0.00     0.00     0.00     3.10     1.20      0   1000
single_obj DDMax       48.71    93.87    49.10    93.89     3.94     1.48   1000   1000
single_obj DDMaxG       0.00     0.00     0.00     0.00     3.12     1.24      0   1000
single_obj erepair     91.69   402.61    91.73   402.60     5.88    16.04    998   1000

Overall Distance Metrics Across All Databases
Alg        Avg BR     σ BR   Avg OR     σ OR
------------------------------------------
DDMax       27.24    71.41    27.31    71.53
erepair     49.50   291.53    49.32   291.56
DDMaxG     710.24   573.21   710.03   573.21
Antlr     1147.00     0.00  1147.00     0.00

Fixed counts for single.db
  Antlr         1
  DDMax      1966
  DDMaxG      931
  erepair    1950

Total fixed files across DBs
  Antlr         1
  DDMax      1966
  DDMaxG      931
  erepair    1950

Perfect repairs in single.db
  single_dot DDMax      373
  single_dot erepair    405
  single_obj DDMax      152
  single_obj erepair    189—

Average runtime in single.db
  Antlr    t=  2.54s (n=2000)
  DDMax    t=  9.41s (n=2000)
  DDMaxG   t= 19.79s (n=2000)
  erepair  t=  3.91s (n=2000)

Overall average runtime across DBs
  Antlr    t=  2.54s  iters=    0.50 (n=2000)
  DDMax    t=  9.41s  iters=  519.52 (n=2000)
  DDMaxG   t= 19.79s  iters= 1313.52 (n=2000)
  erepair  t=  3.91s  iters=  134.88 (n=2000)
```