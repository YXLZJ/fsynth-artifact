
sexp: sexp.c
	gcc -g -o sexp sexp.c
	gcc -fprofile-arcs -ftest-coverage -g -o sexp.cov sexp.c

clean:
	rm -f sexp *.o fmemopen/*.o *.gcda *.gcno fmemopen/*.gcda fmemopen/*.gcno

all: sexp
